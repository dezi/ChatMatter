package de.kappa_mm.email.chatmatter;

import android.support.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class LanguageTags
{
    @SuppressWarnings("unused")
    private final static String LOGTAG = LanguageTags.class.getSimpleName();

    private final static Map<String, String> WASubject;
    private final static Map<String, String> WAAttachment;

    static
    {
        WASubject = new HashMap<>();

        WASubject.put("", "WhatsApp Chat with (.*)");
        WASubject.put("af", "WhatsApp Gesprek met (.*)");
        WASubject.put("ar", "دردشة واتساب مع (.*)");
        WASubject.put("az", "(.*) ilə WhatsApp Söhbəti");
        WASubject.put("bg", "WhatsApp Чат с (.*)");
        WASubject.put("bn", "(.*) এর সাথে WhatsApp চ্যাট");
        WASubject.put("ca", "Xat de WhatsApp amb (.*)");
        WASubject.put("cs", "Chat WhatsApp s uživatelem (.*)");
        WASubject.put("da", "WhatsApp chat med (.*)");
        WASubject.put("de", "WhatsApp Chat mit (.*)");
        WASubject.put("el", "Συνομιλία WhatsApp με (.*)");
        WASubject.put("es", "Chat de WhatsApp con (.*)");
        WASubject.put("et", "WhatsApp\'i vestlus kontaktiga (.*)");
        WASubject.put("fa", "‏گفتگوی WhatsApp با (.*)");
        WASubject.put("fi", "WhatsApp-keskustelu henkilön (.*) kanssa");
        WASubject.put("fr", "Discussion WhatsApp avec (.*)");
        WASubject.put("gu", "(.*) સાથેની WhatsApp વાત");
        WASubject.put("he", "צ\'אט WhatsApp עם (.*)");
        WASubject.put("hi", "(.*) के साथ WhatsApp चैट");
        WASubject.put("hi-v16", "(.*) के साथ WhatsApp चैट");
        WASubject.put("hr", "WhatsApp razgovor s (.*)");
        WASubject.put("hu", "WhatsApp csevegés vele: (.*)");
        WASubject.put("id", "Chat WhatsApp dengan (.*)");
        WASubject.put("in", "Chat WhatsApp dengan (.*)");
        WASubject.put("it", "Chat WhatsApp con (.*)");
        WASubject.put("iw", "צ\'אט WhatsApp עם (.*)");
        WASubject.put("ja", "(.*)とのWhatsAppチャット");
        WASubject.put("kk", "WhatsApp чат: (.*)");
        WASubject.put("kn", "(.*) ಜೊತೆ WhatsApp ಸಂಭಾಷಣೆ");
        WASubject.put("ko", "(.*)님과의 WhatsApp 대화");
        WASubject.put("lt", "WhatsApp pokalbis su (.*)");
        WASubject.put("lv", "WhatsApp sarakste ar (.*)");
        WASubject.put("mk", "WhatsApp разговор со (.*)");
        WASubject.put("ml", "(.*) മായുള്ള WhatsApp ചാറ്റുകൾ");
        WASubject.put("mr", "(.*) सोबतच्या WhatsApp गप्पा");
        WASubject.put("ms", "Sembang WhatsApp dengan (.*)");
        WASubject.put("nb-rNO", "WhatsApp-samtale med (.*)");
        WASubject.put("nl", "WhatsApp-chat met (.*)");
        WASubject.put("pa", "(.*) ਨਾਲ WhatsApp ਗੱਲਬਾਤ");
        WASubject.put("pl", "Czat WhatsApp z (.*)");
        WASubject.put("pt", "Conversa no WhatsApp com (.*)");
        WASubject.put("pt-rBR", "Conversa do WhatsApp com (.*)");
        WASubject.put("ro", "Conversație WhatsApp cu (.*)");
        WASubject.put("ru", "Чат WhatsApp с (.*)");
        WASubject.put("sk", "WhatsApp chat s používateľom (.*)");
        WASubject.put("sl", "WhatsApp klepet z (.*)");
        WASubject.put("sq", "Biseda me (.*) në WhatsApp");
        WASubject.put("sr", "WhatsApp Ћаскање са (.*)");
        WASubject.put("sv", "WhatsApp-chatt med (.*)");
        WASubject.put("sw", "Soga za WhatsApp na (.*)");
        WASubject.put("ta", "(.*) உடன் WhatsApp உரையாடல்");
        WASubject.put("te", "(.*) తో WhatsApp సంభాషణ");
        WASubject.put("th", "แชท WhatsApp กับ (.*)");
        WASubject.put("tl", "WhatsApp Chat kay (.*)");
        WASubject.put("tr", "(.*) ile WhatsApp Sohbeti");
        WASubject.put("uk", "Бесіда WhatsApp з (.*)");
        WASubject.put("ur", "(.*) کے ساتھ WhatsApp بات چیت");
        WASubject.put("uz", "(.*) bilan WhatsApp chati");
        WASubject.put("vi", "Trò chuyện WhatsApp với (.*)");
        WASubject.put("zh-rCN", "WhatsApp 与 (.*) 的对话");
        WASubject.put("zh-rHK", "WhatsApp 與 (.*) 的對話");
        WASubject.put("zh-rSG", "WhatsApp 与 (.*) 的对话");
        WASubject.put("zh-rTW", "WhatsApp 與 (.*) 的對話");

        WAAttachment = new HashMap<>();

        WAAttachment.put("", "(.*) \\(file attached\\)");
        WAAttachment.put("af", "(.*) \\(lêer aangeheg\\)");
        WAAttachment.put("ar", "(.*) \\(الملف مرفق\\)");
        WAAttachment.put("az", "(.*) \\(fayl əlavə olunub\\)");
        WAAttachment.put("bg", "(.*) \\(прикачен файл\\)");
        WAAttachment.put("bn", "(.*) \\(ফাইল সংযুক্ত হয়েছে\\)");
        WAAttachment.put("ca", "(.*) \\(arxiu adjunt\\)");
        WAAttachment.put("cs", "(.*) \\(soubor byl přiložen\\)");
        WAAttachment.put("da", "(.*) \\(fil vedhæftet\\)");
        WAAttachment.put("de", "(.*) \\(Datei angehängt\\)");
        WAAttachment.put("el", "(.*) \\(συνημμένο αρχείο\\)");
        WAAttachment.put("es", "(.*) \\(archivo adjuntado\\)");
        WAAttachment.put("et", "(.*) \\(fail lisatud\\)");
        WAAttachment.put("fa", "‏فایل پیوست شد (.*)");
        WAAttachment.put("fi", "(.*) \\(liitetiedosto\\)");
        WAAttachment.put("fr", "(.*) \\(fichier joint\\)");
        WAAttachment.put("gu", "(.*) \\(ફાઇલ જોડાયેલ\\)");
        WAAttachment.put("he", "(.*) \\(קובץ מצורף\\)");
        WAAttachment.put("hi", "(.*) \\(फाइल अटैच कर दी गई है\\)");
        WAAttachment.put("hi-v16", "(.*) \\(फाइल अटैच कर दी गई है\\)");
        WAAttachment.put("hr", "(.*) \\(datoteka u privitku\\)");
        WAAttachment.put("hu", "(.*) \\(fájl csatolva\\)");
        WAAttachment.put("id", "(.*) \\(file terlampir\\)");
        WAAttachment.put("in", "(.*) \\(file terlampir\\)");
        WAAttachment.put("it", "(.*) \\(file allegato\\)");
        WAAttachment.put("iw", "(.*) \\(קובץ מצורף\\)");
        WAAttachment.put("ja", "(.*) \\(添付ファイル有り\\)");
        WAAttachment.put("kk", "(.*) \\(қосымша файл\\)");
        WAAttachment.put("kn", "(.*) \\(ಕಡತ ಸೇರಿಸಲಾಯಿತು\\)");
        WAAttachment.put("ko", "(.*) \\(파일 첨부됨\\)");
        WAAttachment.put("lt", "(.*) \\(byla prisegta\\)");
        WAAttachment.put("lv", "(.*) \\(pievienots fails\\)");
        WAAttachment.put("mk", "(.*) \\(прикачен фајл\\)");
        WAAttachment.put("ml", "(.*) \\(ഫയൽ ചേർത്തു\\)");
        WAAttachment.put("mr", "(.*) \\(फाइल संलग्न\\)");
        WAAttachment.put("ms", "(.*) \\(fail dilampirkan\\)");
        WAAttachment.put("nb-rNO", "(.*) \\(vedlagt fil\\)");
        WAAttachment.put("nl", "(.*) \\(bestand bijgevoegd\\)");
        WAAttachment.put("pa", "(.*) \\(ਫਾਈਲ ਅਟੈਚ ਕੀਤੀ\\)");
        WAAttachment.put("pl", "(.*) \\(załączony plik\\)");
        WAAttachment.put("pt", "(.*) \\(ficheiro anexado\\)");
        WAAttachment.put("pt-rBR", "(.*) \\(arquivo anexado\\)");
        WAAttachment.put("ro", "(.*) \\(fișier atașat\\)");
        WAAttachment.put("ru", "(.*) \\(файл добавлен\\)");
        WAAttachment.put("sk", "(.*) \\(súbor pripojený\\)");
        WAAttachment.put("sl", "(.*) \\(priložena datoteka\\)");
        WAAttachment.put("sq", "(.*) \\(skedë e ngjitur\\)");
        WAAttachment.put("sr", "(.*) \\(прикачена датотека\\)");
        WAAttachment.put("sv", "(.*) \\(bifogad fil\\)");
        WAAttachment.put("sw", "(.*) \\(faili limeambatanishwa\\)");
        WAAttachment.put("ta", "(.*) இணைக்கப்பட்டது");
        WAAttachment.put("te", "(.*) \\(ఫైల్ జోడించబడినది\\)");
        WAAttachment.put("th", "(.*) \\(ไฟล์ที่แนบ\\)");
        WAAttachment.put("tl", "(.*) \\(nakalakip na file\\)");
        WAAttachment.put("tr", "(.*) \\(dosya ekli\\)");
        WAAttachment.put("uk", "(.*) \\(файл додано\\)");
        WAAttachment.put("ur", "(.*) \\(پیوستہ فائل\\)");
        WAAttachment.put("uz", "(.*) \\(fayl biriktirildi\\)");
        WAAttachment.put("vi", "(.*) \\(tệp đính kèm\\)");
        WAAttachment.put("zh-rCN", "(.*) \\(文件附件\\)");
        WAAttachment.put("zh-rHK", "(.*) \\(附件檔案\\)");
        WAAttachment.put("zh-rSG", "(.*) \\(文件附件\\)");
        WAAttachment.put("zh-rTW", "(.*) \\(附件檔案\\)");
    }

    @Nullable
    public static String getSubjectChatname(String subject)
    {
        if (subject.endsWith(".txt")) subject = subject.substring(0, subject.length() - 4);

        for (Map.Entry<String, String> entry : WASubject.entrySet())
        {
            String regex = entry.getValue();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(subject);

            if (matcher.matches()) return matcher.group(1);
        }

        return null;
    }

    @Nullable
    public static String getAttachment(String message)
    {
        for (Map.Entry<String, String> entry : WAAttachment.entrySet())
        {
            String regex = entry.getValue();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(message);

            if (matcher.matches()) return matcher.group(1);
        }

        return null;
    }
}
