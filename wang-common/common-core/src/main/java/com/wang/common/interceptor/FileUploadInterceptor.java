package com.wang.common.interceptor;


import com.wang.common.config.ConfigService;
import com.wang.common.exception.ErrorCode;
import com.wang.common.exception.UncheckedBusinessException;
import com.wang.common.util.ConfigConstant;
import com.wang.common.util.SpringUtil;
import com.wang.common.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class FileUploadInterceptor implements HandlerInterceptor {

    private static final Set<String> allowedFileType = new HashSet<>(Arrays.asList(
            "xls,xlsx,csv,doc,docx,txt,pdf,cer,jpeg,jpg,pdf".split(",")));
    private static final Set<String> allowedMimeType = new HashSet<>(Arrays.asList(
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" // xlsx
            , "application/vnd.ms-excel" // xls
            , "application/msword" // doc
            , "application/vnd.openxmlformats-officedocument.wordprocessingml.document" // docx
            , "application/pdf" // pdf
            , "text/csv" // csv
            , "text/plain" // txt
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request instanceof MultipartHttpServletRequest && MultipartResolutionDelegate.isMultipartRequest(request)
                // 上传授权文件接口特殊处理fix
                && !request.getRequestURI().contains("insurance/authFile")) {
            ServletRequestContext requestContext = new ServletRequestContext(request);
            long requestSize = requestContext.contentLength();
            ConfigService configService = SpringUtil.getBean(ConfigService.class);
            if (requestSize > (Integer.parseInt(configService.get(ConfigConstant.MAX_FILE_UPLOAD_SIZE, "20")) * 1024 * 1024)) {
                log.info("上传文件大小{}MB, 超过大小限制", requestSize / 1024 / 1024);
                throw new MaxUploadSizeExceededException(requestSize);
            }
            // 限制文件类型
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = multipartRequest.getFileMap();
            for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
                MultipartFile multipartFile = entry.getValue();
                String fileType = StringUtil.getFileType(multipartFile.getOriginalFilename());
                if (!allowedFileType.contains(fileType)) {
                    throw new UncheckedBusinessException(ErrorCode.ERROR_510, "文件格式校验不符合要求");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
