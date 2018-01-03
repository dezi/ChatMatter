package de.kappa_mm.email.chatmatter;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class LanguageTags
{
    @SuppressWarnings("unused")
    private final static String LOGTAG = LanguageTags.class.getSimpleName();

    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_SYSTEM = 1;
    public final static int TYPE_ACTION = 2;

    private final static Map<String, String> WASubject;
    private final static Map<String, String> WAAttachment;
    private final static Map<String, String> WAGroupInfoEncrypted;
    private final static Map<String, String> WAGroupInfoEncryptedStateChanged;

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

        WAGroupInfoEncrypted = new HashMap<>();

        WAGroupInfoEncrypted.put("", "Messages to this group are secured with end-to-end encryption. Tap for more info.");
        WAGroupInfoEncrypted.put("af", "Boodskappe aan hierdie groep word beveilig met end-tot-end enkripsie. Tik vir meer inligting.");
        WAGroupInfoEncrypted.put("ar", "إن الرسائل التي ترسلها إلى هذه المجموعة محمية من خلال التشفير التام. انقر للمزيد من المعلومات.");
        WAGroupInfoEncrypted.put("az", "Bu qrupa göndərdiyiniz ismarıclar bir tərəfdən digərinə qədər şifrələnir. Ətraflı məlumat üçün toxunun.");
        WAGroupInfoEncrypted.put("bg", "Съобщенията, които изпращате към тази група, са подсигурени с криптиране от край до край. Натиснете за повече инфо.");
        WAGroupInfoEncrypted.put("bn", "এই গোষ্ঠীতে পাঠানো বার্তা দুই দিক থেকে এনক্রিপশানের মাধ্যমে নিরাপদ। আরও তথ্যের জন্য আলতো চাপুন।");
        WAGroupInfoEncrypted.put("ca", "Els missatges d\'aquest grup estan assegurats amb encriptació d\'extrem a extrem. Prem aquí per més informació.");
        WAGroupInfoEncrypted.put("cs", "Zprávy pro tuto skupinu jsou zabezpečeny koncovým šifrováním. Další informace zobrazíte klepnutím.");
        WAGroupInfoEncrypted.put("da", "Beskeder til denne gruppe er sikret med kryptering. Tryk for mere info.");
        WAGroupInfoEncrypted.put("de", "Nachrichten an diese Gruppe sind mit Ende-zu-Ende-Verschlüsselung geschützt. Tippe für mehr Infos.");
        WAGroupInfoEncrypted.put("el", "Τα μηνύματα προς αυτήν την ομάδα είναι ασφαλή με κρυπτογράφηση. Πατήστε για πληροφορίες.");
        WAGroupInfoEncrypted.put("es", "Los mensajes en este grupo ahora están protegidos con cifrado de extremo a extremo. Toca para más información.");
        WAGroupInfoEncrypted.put("et", "Selle grupi sõnumid on kaitstud otsast otsani krüpteerimisega. Toksa rohkema info saamiseks.");
        WAGroupInfoEncrypted.put("fa", "‏پیام ها به این گروه با رمزگذاری سرتاسری محفوظ شده اند. برای اطلاعات بیشتر اینجا بزنید.");
        WAGroupInfoEncrypted.put("fi", "Viestit tähän ryhmään salataan täysin. Napauta saadaksesi lisätietoja.");
        WAGroupInfoEncrypted.put("fr", "Les messages envoyés dans ce groupe sont protégés avec le chiffrement de bout en bout. Appuyez pour plus d\'informations.");
        WAGroupInfoEncrypted.put("gu", "આ સમૂહના સંદેશાઓ શરૂઆતથી અંત સુધી ગુપ્તીકરણ દ્વારા સુરક્ષિત છે. વધુ માહિતી માટે ટેપ કરો.");
        WAGroupInfoEncrypted.put("he", "ההודעות הנשלחות בקבוצה זו מאובטחות עם הצפנה מקצה לקצה. הקש/י למידע נוסף.");
        WAGroupInfoEncrypted.put("hi", "इस समूह पर भेजे हुए सन्देश अब शुरू से अंत तक एन्क्रिप्शन से सुरक्षित हैं. अधिक जानकारी के लिए टैप करें.");
        WAGroupInfoEncrypted.put("hi-v16", "इस समूह पर भेजे हुए सन्देश अब शुरू से अंत तक एन्क्रिप्शन से सुरक्षित हैं. अधिक जानकारी के लिए टैप करें.");
        WAGroupInfoEncrypted.put("hr", "Poruke poslane na ovu grupu osigurane su end-to-end metodom šifriranja. Dotaknite za više informacija.");
        WAGroupInfoEncrypted.put("hu", "Az ebben a csoportban lévő üzenetek titkosítottak a végpontok között. Koppints további információkért.");
        WAGroupInfoEncrypted.put("id", "Pesan-pesan yang dikirim ke grup ini diamankan dengan enkripsi end-to-end. Ketuk untuk info selengkapnya.");
        WAGroupInfoEncrypted.put("in", "Pesan-pesan yang dikirim ke grup ini diamankan dengan enkripsi end-to-end. Ketuk untuk info selengkapnya.");
        WAGroupInfoEncrypted.put("it", "I messaggi inviati a questo gruppo sono protetti con la crittografia end-to-end. Tocca per maggiori info.");
        WAGroupInfoEncrypted.put("iw", "ההודעות הנשלחות בקבוצה זו מאובטחות עם הצפנה מקצה לקצה. הקש/י למידע נוסף.");
        WAGroupInfoEncrypted.put("ja", "このグループへのメッセージは、エンドツーエンドの暗号化で保護されます。詳細を見るにはタップしてください。");
        WAGroupInfoEncrypted.put("kk", "Осы топқа жөнелтілген хаттар толығымен шифрлау арқылы қауіпсіз етілді. Толық ақпарат үшін басыңыз.");
        WAGroupInfoEncrypted.put("kn", "ಈ ಗುಂಪಿಗೆ ಸಂದೇಶಗಳು ಈಗ ಕೊನೆಯಿಂದ-ಕೊನೆವರೆಗೆ ಏನ್ಕ್ರಿಪ್ಷನ್ ನಿಂದ ಭದ್ರಪಡಿಸಲ್ಪಟ್ಟಿವೆ. ಹೆಚ್ಚಿನ ಮಾಹಿತಿಗಾಗಿ ತಟ್ಟಿ.");
        WAGroupInfoEncrypted.put("ko", "이 그룹의 메시지는 종단간 암호화되어 안전합니다. 더 많은 정보를 보려면 탭하세요.");
        WAGroupInfoEncrypted.put("lt", "Žinutės į šią grupę dabar yra apsaugotos ištisiniu šifravimu. Bakstelėkite daugiau info.");
        WAGroupInfoEncrypted.put("lv", "Ziņas šajā grupā tagad ir aizsargātas ar pilnīgu šifrēšanu. Pieskarieties, lai uzzinātu vairāk.");
        WAGroupInfoEncrypted.put("mk", "Пораките до оваа група се обезбедени со шифрирање крај-до-крај. Притисни за повеќе инфо.");
        WAGroupInfoEncrypted.put("ml", "ഈ ഗ്രൂപ്പിലേക്കുള്ള സന്ദേശങ്ങൾ ഇപ്പോൾ ആദ്യാവസാന എൻക്രിപ്ഷൻ വഴി സുരക്ഷിതമാണ്. കൂടുതൽ വിവരങ്ങൾക്ക് സ്പർശിക്കൂ.");
        WAGroupInfoEncrypted.put("mr", "या गटामधील संदेश आता संपूर्णपणे कूटबद्ध करून सुरक्षित केलेले आहेत. अधिक माहितीसाठी टॅप करा.");
        WAGroupInfoEncrypted.put("ms", "Mesej-mesej ke grup ini kini selamat dengan penyulitan hujung ke hujung. Ketik untuk lebih info.");
        WAGroupInfoEncrypted.put("nb-rNO", "Meldinger til denne gruppen er nå kryptert fra ende til ende. Trykk for mer informasjon.");
        WAGroupInfoEncrypted.put("nl", "Berichten die naar deze groep worden verzonden, zijn beveiligd met end-to-end encryptie. Tik voor meer informatie.");
        WAGroupInfoEncrypted.put("pa", "ਇਹ ਗਰੁੱਪ ਲਈ ਸੁਨੇਹੇ ਹੁਣ ਸਿਰੇ ਤੋਂ ਸਿਰੇ ਤੱਕ ਇੰਕ੍ਰਿਪਸ਼ਨ ਨਾਲ ਸੁਰੱਖਿਅਤ ਕੀਤੇ ਹਨ। ਹੋਰ ਜਾਣਕਾਰੀ ਲਈ ਟੈਪ ਕਰੋ।");
        WAGroupInfoEncrypted.put("pl", "Wiadomości do tej grupy są zabezpieczone przez pełne szyfrowanie. Więcej informacji tutaj.");
        WAGroupInfoEncrypted.put("pt", "As mensagens que enviar a este grupo serão completamente encriptadas. Toque para saber mais.");
        WAGroupInfoEncrypted.put("pt-rBR", "As mensagens para este grupo estão protegidas com criptografia de ponta-a-ponta. Toque para obter mais informações.");
        WAGroupInfoEncrypted.put("ro", "Mesajele către acest grup sunt securizate prin criptare integrală. Apăsați pentru mai multe detalii.");
        WAGroupInfoEncrypted.put("ru", "Сообщения в данной группе защищены сквозным шифрованием. Узнать больше.");
        WAGroupInfoEncrypted.put("sk", "Správy pre túto skupinu sú zabezpečené šifrovaním počas celého spojenia. Viac informácií zobrazíte klepnutím.");
        WAGroupInfoEncrypted.put("sl", "Sporočila v tej skupini so zavarovana s šifriranjem od konca do konca. Pritisnite za več informacij.");
        WAGroupInfoEncrypted.put("sq", "Mesazhet që i dërgoni në këtë grup janë të koduara fund-e-krye. Prekni këtu për më shumë informata.");
        WAGroupInfoEncrypted.put("sr", "Поруке ка овој групи су сада обезбеђене са шифровањем од почетка-до-краја. Додирни за више информација.");
        WAGroupInfoEncrypted.put("sv", "Meddelanden till denna grupp är säkrade med komplett kryptering. Tryck för mer info.");
        WAGroupInfoEncrypted.put("sw", "Jumbe kwenye kikundi hiki zipo salama kwa ufumbaji wa mwisho-kwa-mwisho. Gusa kwa maelezo zaidi.");
        WAGroupInfoEncrypted.put("ta", "இக்குழுவிற்கு தாங்கள் அனுப்பும் தகவல்கள் முழு மறையாக்கத்துடன் பாதுகாக்கப்படுகிறது. விவரத்திற்கு தட்டுக.");
        WAGroupInfoEncrypted.put("te", "ఈ వర్గానికి సందేశాలు ఎండ్ టు ఎండ్ ఎన్క్రిప్షన్ తో సురక్షితం ఉంటాయి. మరింత సమాచారం కోసం నొక్కండి.");
        WAGroupInfoEncrypted.put("th", "ข้อความที่ส่งถึงกลุ่มนี้มีความปลอดภัยด้วยการเข้ารหัสจากต้นทางถึงปลายทาง แตะสำหรับข้อมูลเพิ่มเติม");
        WAGroupInfoEncrypted.put("tl", "Ang mga mensahe sa grupong ito ay secured gamit ang end-to-end encryption. I-tap para sa dagdag na info.");
        WAGroupInfoEncrypted.put("tr", "Bu gruba gönderdiğiniz mesajlar artık uçtan uca şifreleme ile korunmaktadır. Daha fazla bilgi için dokunun.");
        WAGroupInfoEncrypted.put("uk", "Повідомлення у цій групі захищено наскрізним шифруванням. Натисніть, щоб дізнатися більше.");
        WAGroupInfoEncrypted.put("ur", "اس گروپ کے پیغامات شروع سے آخر تک رمزکاری کے ذریعے محفوظ ہیں۔ مزید معلومات کے لیے ٹیپ کریں۔");
        WAGroupInfoEncrypted.put("uz", "Ushbu guruhdagi xabarlar boshidan oxirigacha shifrlash bilan himoyalanadi. Batafsil ma’lumot uchun bosing.");
        WAGroupInfoEncrypted.put("vi", "Các tin nhắn gửi tới nhóm này đã được bảo mật với mã hoá đầu-cuối. Nhấn để biết thêm thông tin.");
        WAGroupInfoEncrypted.put("zh-rCN", "此群组中的信息进行了端到端的加密，点击获取更多信息。");
        WAGroupInfoEncrypted.put("zh-rHK", "此群組中的訊息和通話進行了端對端的加密，點擊獲取更多資訊。");
        WAGroupInfoEncrypted.put("zh-rSG", "此群组中的信息进行了端到端的加密，点击获取更多信息。");
        WAGroupInfoEncrypted.put("zh-rTW", "此群組中的訊息和通話進行了端對端的加密，點擊獲取更多資訊。");

        WAGroupInfoEncryptedStateChanged = new HashMap<>();

        WAGroupInfoEncryptedStateChanged.put("", "Messages to this group are now secured with end-to-end encryption. Tap for more info.");
        WAGroupInfoEncryptedStateChanged.put("af", "Boodskappe aan hierdie groep word nou beveilig met end-tot-end enkripsie. Tik vir meer inligting.");
        WAGroupInfoEncryptedStateChanged.put("ar", "باتت الرسائل التي ترسلها إلى هذه المجموعة محمية من خلال التشفير التام. انقر للمزيد من المعلومات.");
        WAGroupInfoEncryptedStateChanged.put("az", "Bu qrupa göndərdiyiniz ismarıclar artıq bir başdan digərinə qədər şifrələnir. Ətraflı məlumat üçün toxunun.");
        WAGroupInfoEncryptedStateChanged.put("bg", "Съобщенията, които изпращате към тази група, вече са подсигурени с криптиране от край до край. Натиснете за повече информация.");
        WAGroupInfoEncryptedStateChanged.put("bn", "এই গোষ্ঠীর বার্তাগুলো দুই দিক থেকে এনক্রিপশানের মাধ্যমে নিরাপদ। আরও তথ্যের জন্য আলতো চাপুন।");
        WAGroupInfoEncryptedStateChanged.put("ca", "Els missatges d\'aquest grup estan ara assegurats amb encriptació d\'extrem a extrem. Prem aquí per més informació.");
        WAGroupInfoEncryptedStateChanged.put("cs", "Zprávy pro tuto skupinu jsou teď zabezpečeny koncovým šifrováním. Další informace zobrazíte klepnutím.");
        WAGroupInfoEncryptedStateChanged.put("da", "Beskeder til denne gruppe er nu sikret med kryptering. Tryk for mere info.");
        WAGroupInfoEncryptedStateChanged.put("de", "Nachrichten an diese Gruppe sind jetzt mit Ende-zu-Ende-Verschlüsselung geschützt. Tippe für mehr Infos.");
        WAGroupInfoEncryptedStateChanged.put("el", "Τα μηνύματα προς αυτήν την ομάδα είναι τώρα ασφαλή με κρυπτογράφηση. Πατήστε για πληροφορίες.");
        WAGroupInfoEncryptedStateChanged.put("es", "Los mensajes en este grupo ahora están protegidos con cifrado de extremo a extremo. Toca para más información.");
        WAGroupInfoEncryptedStateChanged.put("et", "Selle grupi sõnumid on nüüd kaitstud otsast otsani krüpteerimisega. Toksa rohkema info saamiseks.");
        WAGroupInfoEncryptedStateChanged.put("fa", "‏پیام ها به این گروه هم اکنون با رمزگذاری سرتاسری محفوظ شده اند. برای اطلاعات بیشتر اینجا بزنید.");
        WAGroupInfoEncryptedStateChanged.put("fi", "Viestit tähän ryhmään salataan nyt täysin. Napauta saadaksesi lisätietoja.");
        WAGroupInfoEncryptedStateChanged.put("fr", "Les messages envoyés dans ce groupe sont désormais protégés avec le chiffrement de bout en bout. Appuyez pour plus d\'informations.");
        WAGroupInfoEncryptedStateChanged.put("gu", "આ સમૂહના સંદેશાઓ હવે શરૂઆતથી અંત સુધી ગુપ્તીકરણ દ્વારા સુરક્ષિત છે. વધુ માહિતી માટે ટેપ કરો.");
        WAGroupInfoEncryptedStateChanged.put("he", "כעת ההודעות הנשלחות בקבוצה זו מאובטחות עם הצפנה מקצה לקצה. הקש/י למידע נוסף.");
        WAGroupInfoEncryptedStateChanged.put("hi", "इस समूह में भेजे गए संदेश शुरू से अंत तक एन्क्रिप्शन से सुरक्षित हैं. अधिक जानकारी के लिए टैप करें.");
        WAGroupInfoEncryptedStateChanged.put("hi-v16", "इस समूह में भेजे गए संदेश शुरू से अंत तक एन्क्रिप्शन से सुरक्षित हैं. अधिक जानकारी के लिए टैप करें.");
        WAGroupInfoEncryptedStateChanged.put("hr", "Poruke poslane na ovu grupu sad su osigurane end-to-end metodom šifriranja. Dotaknite za više informacija.");
        WAGroupInfoEncryptedStateChanged.put("hu", "Az ebben a csoportban lévő üzenetek titkosítottak a végpontok között. Koppints további információkért.");
        WAGroupInfoEncryptedStateChanged.put("id", "Pesan-pesan yang dikirim ke grup ini kini diamankan dengan enkripsi end-to-end. Ketuk untuk info selengkapnya.");
        WAGroupInfoEncryptedStateChanged.put("in", "Pesan-pesan yang dikirim ke grup ini kini diamankan dengan enkripsi end-to-end. Ketuk untuk info selengkapnya.");
        WAGroupInfoEncryptedStateChanged.put("it", "I messaggi inviati a questo gruppo sono ora protetti con la crittografia end-to-end. Tocca per maggiori info.");
        WAGroupInfoEncryptedStateChanged.put("iw", "כעת ההודעות הנשלחות בקבוצה זו מאובטחות עם הצפנה מקצה לקצה. הקש/י למידע נוסף.");
        WAGroupInfoEncryptedStateChanged.put("ja", "このグループへのメッセージは、エンドツーエンドの暗号化で保護されています。詳細を見るにはタップしてください。");
        WAGroupInfoEncryptedStateChanged.put("kk", "Осы топқа жөнелтілген хаттар толығымен шифрлау арқылы қауіпсіз етілді. Толық ақпарат үшін басыңыз.");
        WAGroupInfoEncryptedStateChanged.put("kn", "ಈ ಗುಂಪಿಗೆ ಸಂದೇಶಗಳು ಈಗ ಕೊನೆಯಿಂದ-ಕೊನೆವರೆಗೆ ಏನ್ಕ್ರಿಪ್ಷನ್ ನಿಂದ ಭದ್ರಪಡಿಸಲ್ಪಟ್ಟಿವೆ. ಹೆಚ್ಚಿನ ಮಾಹಿತಿಗಾಗಿ ತಟ್ಟಿ.");
        WAGroupInfoEncryptedStateChanged.put("ko", "이 그룹의 메시지는 이제 종단간 암호화되어 안전합니다. 더 많은 정보를 보려면 탭하세요.");
        WAGroupInfoEncryptedStateChanged.put("lt", "Žinutės į šią grupę dabar yra apsaugotos ištisiniu šifravimu. Bakstelėkite daugiau info.");
        WAGroupInfoEncryptedStateChanged.put("lv", "Ziņas šajā grupā tagad ir aizsargātas ar pilnīgu šifrēšanu. Pieskarieties, lai uzzinātu vairāk.");
        WAGroupInfoEncryptedStateChanged.put("mk", "Пораките до оваа група сега се обезбедени со шифрирање крај-до-крај. Притисни за повеќе инфо.");
        WAGroupInfoEncryptedStateChanged.put("ml", "ഈ ഗ്രൂപ്പിലേക്കുള്ള സന്ദേശങ്ങൾ ഇപ്പോൾ ആദ്യാവസാന എൻക്രിപ്ഷൻ വഴി സുരക്ഷിതമാണ്. കൂടുതൽ വിവരങ്ങൾക്ക് സ്പർശിക്കൂ.");
        WAGroupInfoEncryptedStateChanged.put("mr", "या गटामधील संदेश आता संपूर्णपणे कूटबद्ध करून सुरक्षित केलेले आहेत. अधिक माहितीसाठी टॅप करा.");
        WAGroupInfoEncryptedStateChanged.put("ms", "Mesej-mesej ke grup ini kini selamat dengan penyulitan hujung ke hujung. Ketik untuk lebih info.");
        WAGroupInfoEncryptedStateChanged.put("nb-rNO", "Meldinger til denne gruppen er nå kryptert fra ende til ende. Trykk for mer informasjon.");
        WAGroupInfoEncryptedStateChanged.put("nl", "Berichten die naar deze groep worden verzonden, zijn nu beveiligd met end-to-end encryptie. Tik voor meer informatie.");
        WAGroupInfoEncryptedStateChanged.put("pa", "ਇਹ ਗਰੁੱਪ ਲਈ ਸੁਨੇਹੇ ਹੁਣ ਸਿਰੇ ਤੋਂ ਸਿਰੇ ਤੱਕ ਇੰਕ੍ਰਿਪਸ਼ਨ ਨਾਲ ਸੁਰੱਖਿਅਤ ਕੀਤੇ ਹਨ। ਹੋਰ ਜਾਣਕਾਰੀ ਲਈ ਟੈਪ ਕਰੋ।");
        WAGroupInfoEncryptedStateChanged.put("pl", "Wiadomości do tej grupy są teraz zabezpieczone przez pełne szyfrowanie. Więcej informacji tutaj.");
        WAGroupInfoEncryptedStateChanged.put("pt", "As mensagens que enviar a este grupo passarão a ser completamente encriptadas. Toque para saber mais.");
        WAGroupInfoEncryptedStateChanged.put("pt-rBR", "As mensagens enviadas a este grupo estão agora protegidas com a criptografia de ponta-a-ponta. Toque para obter mais informações.");
        WAGroupInfoEncryptedStateChanged.put("ro", "Mesajele către acest grup sunt acum securizate prin criptare integrală. Apăsați pentru mai multe detalii.");
        WAGroupInfoEncryptedStateChanged.put("ru", "Сообщения в данной группе теперь защищены сквозным шифрованием. Узнать больше.");
        WAGroupInfoEncryptedStateChanged.put("sk", "Správy pre túto skupinu sú teraz zabezpečené šifrovaním počas celého spojenia. Viac informácií zobrazíte klepnutím.");
        WAGroupInfoEncryptedStateChanged.put("sl", "Sporočila v tej skupini so sedaj zavarovana s šifriranjem od konca do konca. Pritisnite za več informacij.");
        WAGroupInfoEncryptedStateChanged.put("sq", "Mesazhet që i shkëmbeni në këtë grup janë tani të koduara fund-e-krye. Prekni këtu për më shumë informata.");
        WAGroupInfoEncryptedStateChanged.put("sr", "Поруке ка овој групи су сада обезбеђене са шифровањем од почетка-до-краја. Додирни за више информација.");
        WAGroupInfoEncryptedStateChanged.put("sv", "Meddelanden till denna grupp är nu säkrade med komplett kryptering. Tryck för mer info.");
        WAGroupInfoEncryptedStateChanged.put("sw", "Jumbe kwenye kikundi hiki sasa zipo salama kwa ufumbaji wa mwisho-kwa-mwisho. Gusa kwa maelezo zaidi.");
        WAGroupInfoEncryptedStateChanged.put("ta", "இக்குழுவிற்கு தாங்கள் அனுப்பும் தகவல்கள் முழு மறையாக்கத்துடன் பாதுகாக்கப்படுகிறது. விவரத்திற்கு தட்டுக.");
        WAGroupInfoEncryptedStateChanged.put("te", "ఈ వర్గానికి సందేశాలు ఇప్పుడు ఎండ్ టు ఎండ్ ఎన్క్రిప్షన్ తో సురక్షితం ఉంటాయి. మరింత సమాచారం కోసం నొక్కండి.");
        WAGroupInfoEncryptedStateChanged.put("th", "ข้อความที่คุณส่งถึงกลุ่มนี้ขณะนี้มีความปลอดภัยด้วยการเข้ารหัสจากต้นทางถึงปลายทาง แตะสำหรับข้อมูลเพิ่มเติม");
        WAGroupInfoEncryptedStateChanged.put("tl", "Ang mensahe sa grupong ito ay secured na, gamit ang end-to-end encryption. I-tap para sa dagdag na info.");
        WAGroupInfoEncryptedStateChanged.put("tr", "Bu gruba gönderdiğiniz mesajlar artık uçtan uca şifreleme ile korunmaktadır. Daha fazla bilgi için dokunun.");
        WAGroupInfoEncryptedStateChanged.put("uk", "Повідомлення у цій групі тепер захищено наскрізним шифруванням. Натисніть, щоб дізнатися більше.");
        WAGroupInfoEncryptedStateChanged.put("ur", "اس گروپ کے پیغامات اب شروع سے آخر تک رمزکاری کے ذریعے محفوظ ہیں۔ مزید معلومات کے لیے ٹیپ کریں۔");
        WAGroupInfoEncryptedStateChanged.put("uz", "Ushbu guruhdagi xabarlar endi boshidan oxirigacha shifrlash bilan himoyalanadi. Batafsil ma’lumot uchun bosing.");
        WAGroupInfoEncryptedStateChanged.put("vi", "Những tin nhắn gửi tới nhóm này được bảo mật với mã hoá đầu-cuối. Nhấn để biết thêm thông tin.");
        WAGroupInfoEncryptedStateChanged.put("zh-rCN", "此群组中的信息已进行端到端的加密，点击获取更多信息。");
        WAGroupInfoEncryptedStateChanged.put("zh-rHK", "此群組中的訊息已進行端對端的加密，點擊獲取更多資訊。");
        WAGroupInfoEncryptedStateChanged.put("zh-rSG", "此群组中的信息已进行端到端的加密，点击获取更多信息。");
        WAGroupInfoEncryptedStateChanged.put("zh-rTW", "此群組中的訊息已進行端對端的加密，點擊獲取更多資訊。");
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

    public static int getMessageType(String message)
    {
        for (Map.Entry<String, String> entry : WAGroupInfoEncrypted.entrySet())
        {
            String sample = entry.getValue();

            if (entry.getKey().equals("de"))
            {
                Log.d(LOGTAG, "##### m=" + message);
                Log.d(LOGTAG, "##### t=" + sample);
            }

            if (sample.equals(message)) return TYPE_SYSTEM;
        }

        for (Map.Entry<String, String> entry : WAGroupInfoEncryptedStateChanged.entrySet())
        {
            String sample = entry.getValue();

            if (entry.getKey().equals("de"))
            {
                Log.d(LOGTAG, "##### m=" + message);
                Log.d(LOGTAG, "##### t=" + sample);
            }

            if (sample.equals(message)) return TYPE_SYSTEM;
        }

        return TYPE_NORMAL;
    }
}
