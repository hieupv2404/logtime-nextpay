package com.example.emaillogtime.service.impl;

import com.example.emaillogtime.dto.DataMailDTO;
//import com.example.emaillogtime.dto.sdt.ClientSdi;
import com.example.emaillogtime.dto.EntriesTimeDTO;
import com.example.emaillogtime.entity.EntriesTime;
import com.example.emaillogtime.entity.User;
import com.example.emaillogtime.reposiotry.EntriesTimeDtoRepository;
import com.example.emaillogtime.reposiotry.EntriesTimeRepository;
import com.example.emaillogtime.reposiotry.UserRepository;
import com.example.emaillogtime.service.ClientService;
import com.example.emaillogtime.service.MailService;
//import com.example.emaillogtime.utils.Const;
//import com.example.emaillogtime.utils.DataUtils;
import com.example.emaillogtime.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntriesTimeRepository entriesTimeRepository;

    @Autowired
    EntriesTimeDtoRepository entriesTimeDtoRepository;

    @Override
    public Boolean create(DataMailDTO dataMailDTO) {
        User user = new User();
        EntriesTime entriesTime = new EntriesTime();
        try {
            List<String> listEmail = userRepository.getAllEmail();
            for (String email : listEmail) {
                user = userRepository.findUserByMail(email);
//                List<Float> hours = entries_timeRepository.getAllHoursByUserId(user.getUserId());
                List<Float> hours = entriesTimeRepository.getAllHoursAndAndDate(user.getUserId());
                float sum =0;
                for (Float hours1 : hours) {
                    sum += hours1;

                }
                if (sum < 8) {
                    dataMailDTO.setTo(email);
                    dataMailDTO.setSubject("NH???C NH??? LOGTIME");
//                    dataMailDTO.setContent("H??m nay b???n ch??a logtime ????? 8 ti???ng. </br> \n "+
//                            "S??? gi??? logtime c??n thi???u ng??y h??m nay:" + (8 - sum));
                    dataMailDTO.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                            "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                            "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                            "<head>\n" +
                            "    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>\n" +
                            "    <style>\n" +
                            "        body {\n" +
                            "            font-family: 'Roboto', sans-serif;\n" +
                            "            font-size: 48px;\n" +
                            "        }\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<p>Dear Anh/Ch???,</p>\n" +
                            "<p>G???i nh???c nh??? logtime ?????n anh/ch???: </p>\n" +
                            "<p>H??m nay anh/ch??? ch??a logtime ????? 8 ti???ng  </p>\n" +
                            "<p>S??? gi??? Logtime hi???n t???i: " + sum + "</p>\n"+
                            "<p>S??? gi??? Logtime anh/ch??? c??n thi???u ng??y h??m nay l?? : " + (8-sum) + "</p>\n" +
                            "<p>????y l?? email t??? ?????ng kh??ng c???n tr??? l???i!</p>\n" +
                            "<h4>\"NEXTTERS C??NG NHAU CHUNG TAY, CH??NG TA QUY???T TH???NG D???CH B???NH\"</h4>"+
                            "<h5>Tr??n tr???ng!</h5>"+
                            "<tr style=\"height:178.85pt\"><td width=\"212\" valign=\"top\" style=\"width:159.35pt;padding:0in 5.4pt;height:178.85pt\"><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><br></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><b><span style=\"font-size:9pt;color:rgb(11,83,148)\">Best Regards,</span></b><span style=\"font-size:9pt;color:rgb(11,83,148)\">&nbsp;<u></u><u></u></span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\"><u></u>&nbsp;</span><u style=\"font-size:9pt\"><img src=\"https://images02.vietnamworks.com/companyprofile/migrate/uploads/2016/07/Nexttech-logo-3.png\" width=\"200\" height=\"200\"></u></font></p></td><td width=\"458\" valign=\"top\" style=\"width:343.35pt;padding:0in 5.4pt;height:178.85pt\"><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><span style=\"font-size:9pt\"><font face=\"georgia, serif\"><u></u>&nbsp;<u></u></font></span></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><b><span style=\"font-size:9pt;color:rgb(31,78,121)\"><font face=\"georgia, serif\">Human Resource Department&nbsp;</font></span></b></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><b><span style=\"font-size:9pt;color:rgb(46,116,181)\">NextTech - Peacesoft Group</span></b><span style=\"font-size:9pt\"><u></u><u></u></span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\">Tel: (+84) - (0)&nbsp;</span><span style=\"font-size:12.8px\">24 730 666 96 (Ext. 0110)</span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><span style=\"font-size:9pt\"><font face=\"georgia, serif\">Address : 3rd Floor, VTC Building, 18th Tam Trinh Str., Hai Ba Trung Dist., Hanoi&nbsp;<u></u><u></u></font></span></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\">Website: &nbsp;</span><span style=\"font-size:9pt\"><a href=\"http://nexttech.asia/\" style=\"color:rgb(17,85,204)\" target=\"_blank\" rel=\"noopener noreferrer\">nexttech.asia</a></span><span style=\"font-size:9pt\">&nbsp;</span><font style=\"font-size:9pt\">&nbsp;&nbsp;</font></font></p></td></tr>"+
                            "</body>\n" +
                            "</html>\n");
                    mailService.sendMail(dataMailDTO, "client");
                }
            }
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean createTuan(DataMailDTO dataMailDTO) {
        User user = new User();
        EntriesTime entriesTime = new EntriesTime();
        Float entriesTimeDTO = entriesTimeDtoRepository.getEntriesTimeDTO();
        try {
            List<String> listEmail = userRepository.getAllEmail();
            for (String email : listEmail) {
                user = userRepository.findUserByMail(email);
                List<Float> hours = entriesTimeRepository.getAllHoursAndAndDateOk(user.getUserId(), PageRequest.of(0, 3));
                float sum = 0;
                for (Float hours1 : hours) {
                    sum += hours1;
                }
                if (entriesTimeDTO == 44) {
                    if (sum < entriesTime.getSumHours()) {
                        entriesTime.setGioThieu(entriesTime.getSumHours() - sum);
                        dataMailDTO.setTo(email);
                        dataMailDTO.setSubject("NH???C NH??? LOGTIME");
//                    dataMailDTO.setContent("Gi??? c??n thi???u :" + ( logtime.getSum() - ((logtime.getLogTime()) * 5 + 4)));
                        dataMailDTO.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                                "<head>\n" +
                                "    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>\n" +
                                "    <style>\n" +
                                "        body {\n" +
                                "            font-family: 'Roboto', sans-serif;\n" +
                                "            font-size: 48px;\n" +
                                "        }\n" +
                                "    </style>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "<p>Dear Anh/Ch???,</p>\n" +
                                "<p>G???i nh???c nh??? logtime ?????n anh/ch???: </p>\n" +
                                "<p>Tu???n n??y anh/ch??? ch??a logtime ?????!  </p>\n" +
                                "<p>S??? gi??? Logtime hi???n t???i: " + sum + "</p>\n"+
                                "<p>S??? gi??? Logtime t???i thi???u l?? : " + entriesTime.getSumHours() + "</p>\n"+
                                "<p>S??? gi??? Logtime anh/ch??? c??n thi???u l?? : " + (entriesTime.getSumHours() - sum) + "</p>\n" +
                                "<p>????y l?? email t??? ?????ng kh??ng c???n tr??? l???i!</p>\n" +
                                "<h4>\"NEXTTERS C??NG NHAU CHUNG TAY, CH??NG TA QUY???T TH???NG D???CH B???NH\"</h4>"+
                                "<h5>Tr??n tr???ng!</h5>"+
                                "<tr style=\"height:178.85pt\"><td width=\"212\" valign=\"top\" style=\"width:159.35pt;padding:0in 5.4pt;height:178.85pt\"><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><br></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><b><span style=\"font-size:9pt;color:rgb(11,83,148)\">Best Regards,</span></b><span style=\"font-size:9pt;color:rgb(11,83,148)\">&nbsp;<u></u><u></u></span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\"><u></u>&nbsp;</span><u style=\"font-size:9pt\"><img src=\"https://images02.vietnamworks.com/companyprofile/migrate/uploads/2016/07/Nexttech-logo-3.png\" width=\"200\" height=\"200\"></u></font></p></td><td width=\"458\" valign=\"top\" style=\"width:343.35pt;padding:0in 5.4pt;height:178.85pt\"><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><span style=\"font-size:9pt\"><font face=\"georgia, serif\"><u></u>&nbsp;<u></u></font></span></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><b><span style=\"font-size:9pt;color:rgb(31,78,121)\"><font face=\"georgia, serif\">Human Resource Department&nbsp;</font></span></b></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><b><span style=\"font-size:9pt;color:rgb(46,116,181)\">NextTech - Peacesoft Group</span></b><span style=\"font-size:9pt\"><u></u><u></u></span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\">Tel: (+84) - (0)&nbsp;</span><span style=\"font-size:12.8px\">24 730 666 96 (Ext. 0110)</span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><span style=\"font-size:9pt\"><font face=\"georgia, serif\">Address : 3rd Floor, VTC Building, 18th Tam Trinh Str., Hai Ba Trung Dist., Hanoi&nbsp;<u></u><u></u></font></span></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\">Website: &nbsp;</span><span style=\"font-size:9pt\"><a href=\"http://nexttech.asia/\" style=\"color:rgb(17,85,204)\" target=\"_blank\" rel=\"noopener noreferrer\">nexttech.asia</a></span><span style=\"font-size:9pt\">&nbsp;</span><font style=\"font-size:9pt\">&nbsp;&nbsp;</font></font></p></td></tr>"+
                                "</body>\n" +
                                "</html>\n");
                        mailService.sendMail(dataMailDTO, "client");
                    }
                } else {
                    if (sum < entriesTimeDTO) {
                        entriesTime.setGioThieu(entriesTimeDTO - sum);
                        dataMailDTO.setTo(email);
                        dataMailDTO.setSubject("NH???C NH??? LOGTIME");
//                    dataMailDTO.setContent("Gi??? c??n thi???u :" + ( logtime.getSum() - ((logtime.getLogTime()) * 5 + 4)));
                        dataMailDTO.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                                "<head>\n" +
                                "    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>\n" +
                                "    <style>\n" +
                                "        body {\n" +
                                "            font-family: 'Roboto', sans-serif;\n" +
                                "            font-size: 48px;\n" +
                                "        }\n" +
                                "    </style>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "<p>Dear Anh/Ch???,</p>\n" +
                                "<p>G???i nh???c nh??? logtime ?????n anh/ch???: </p>\n" +
                                "<p>Tu???n n??y anh/ch??? ch??a logtime ?????!   </p>\n" +
                                "<p>S??? gi??? Logtime hi???n t???i: " + sum + "</p>\n"+
                                "<p>S??? gi??? Logtime t???i thi???u l?? : " + entriesTimeDTO + "</p>\n"+
                                "<p>S??? gi??? Logtime anh/ch??? c??n thi???u l?? :  " + (entriesTimeDTO - sum) + "</p>\n" +
                                "<p>????y l?? email t??? ?????ng kh??ng c???n tr??? l???i!</p>\n" +
                                "<h4>\"NEXTTERS C??NG NHAU CHUNG TAY, CH??NG TA QUY???T TH???NG D???CH B???NH\"</h4>"+
                                "<h5>Tr??n tr???ng!</h5>"+
                                "--"+
                                "<tr style=\"height:178.85pt\"><td width=\"212\" valign=\"top\" style=\"width:159.35pt;padding:0in 5.4pt;height:178.85pt\"><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><br></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><b><span style=\"font-size:9pt;color:rgb(11,83,148)\">Best Regards,</span></b><span style=\"font-size:9pt;color:rgb(11,83,148)\">&nbsp;<u></u><u></u></span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\"><u></u>&nbsp;</span><u style=\"font-size:9pt\"><img src=\"https://images02.vietnamworks.com/companyprofile/migrate/uploads/2016/07/Nexttech-logo-3.png\" width=\"200\" height=\"200\"></u></font></p></td><td width=\"458\" valign=\"top\" style=\"width:343.35pt;padding:0in 5.4pt;height:178.85pt\"><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><span style=\"font-size:9pt\"><font face=\"georgia, serif\"><u></u>&nbsp;<u></u></font></span></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><b><span style=\"font-size:9pt;color:rgb(31,78,121)\"><font face=\"georgia, serif\">Human Resource Department&nbsp;</font></span></b></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><b><span style=\"font-size:9pt;color:rgb(46,116,181)\">NextTech - Peacesoft Group</span></b><span style=\"font-size:9pt\"><u></u><u></u></span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\">Tel: (+84) - (0)&nbsp;</span><span style=\"font-size:12.8px\">24 730 666 96 (Ext. 0110)</span></font></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><span style=\"font-size:9pt\"><font face=\"georgia, serif\">Address : 3rd Floor, VTC Building, 18th Tam Trinh Str., Hai Ba Trung Dist., Hanoi&nbsp;<u></u><u></u></font></span></p><p style=\"margin-right:0in;margin-left:0in;font-size:12pt\"><font face=\"georgia, serif\"><span style=\"font-size:9pt\">Website: &nbsp;</span><span style=\"font-size:9pt\"><a href=\"http://nexttech.asia/\" style=\"color:rgb(17,85,204)\" target=\"_blank\" rel=\"noopener noreferrer\">nexttech.asia</a></span><span style=\"font-size:9pt\">&nbsp;</span><font style=\"font-size:9pt\">&nbsp;&nbsp;</font></font></p></td></tr>"+
                                "</body>\n" +
                                "</html>\n");
                        mailService.sendMail(dataMailDTO, "client");
                    }
                }
            }
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
