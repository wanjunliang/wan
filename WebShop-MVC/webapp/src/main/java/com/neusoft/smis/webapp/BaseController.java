package com.neusoft.smis.webapp;



import com.neusoft.smis.service.DataAnalysisService.DataAnalysisService;
import com.neusoft.smis.service.company_web_service.NewsService;
import com.neusoft.smis.service.detail_info_service.Detail_info_service;
import com.neusoft.smis.service.login_register_service.LoginRegisterService;
import com.neusoft.smis.service.message_service.MessageService;
import com.neusoft.smis.service.rob_task_service.RobTaskServcie;
import com.neusoft.smis.service.tdzyfSifaPaimaiService.TdzyfSifaPaimaiService;
import com.neusoft.smis.service.user_manage_service.UserService;
import com.neusoft.smis.service.weixinService.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2016/12/21.
 */
@Controller
public class BaseController {
    @Autowired
    public Detail_info_service detail_info_service;
    @Autowired
    public UserService userService;
    @Autowired
    public LoginRegisterService loginRegisterService;
    @Autowired
    public NewsService newsService;
    @Autowired
    public TdzyfSifaPaimaiService tdzyfSifaPaimaiService;
    @Autowired
    public MessageService messageService;
    @Autowired
    public RobTaskServcie robTaskServcie;
    @Autowired
    public WeixinService weixinService;//微信端的服务

    @Autowired
    public DataAnalysisService dataAnalysisService;
}
