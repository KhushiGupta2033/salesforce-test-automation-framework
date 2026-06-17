package reusableClass;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.mail.*;
import jakarta.mail.search.SubjectTerm;

public class FetchOtpUsingSubject {

    public static String getOtp() {

        String host = "imap.gmail.com";
        String username = "acckg21@gmail.com";
        String password = "qfah wjse auyq zfei";

        String expectedSubject = "Verify your identity in Salesforce";

        int maxWaitTime = 30;   // seconds
        int intervals = 5;      // seconds

        String otp = null;

        try {

            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            int waited = 0;

            while (waited < maxWaitTime) {

                Message[] messages =
                        emailFolder.search(new SubjectTerm(expectedSubject));

                for (int i = messages.length - 1; i >= 0; i--) {

                    Message message = messages[i];

                    String content = message.getContent().toString();

                    Pattern pattern = Pattern.compile("\\d{6}");
                    Matcher matcher = pattern.matcher(content);

                    if (matcher.find()) {

                        otp = matcher.group();
                        System.out.println("OTP Found: " + otp);

                        emailFolder.close(false);
                        store.close();

                        return otp;   
                    }
                }

                Thread.sleep(intervals * 1000);
                waited += intervals;
            }

            emailFolder.close(false);
            store.close();

        }

        catch (Exception e) {
            System.out.println("Fetching OTP failed\n" + e);
        }

        return otp;
    }
}