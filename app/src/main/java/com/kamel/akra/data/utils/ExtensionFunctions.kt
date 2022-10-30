package com.kamel.akra.data.utils

import android.content.res.Resources
import com.kamel.akra.R
import com.kamel.akra.app.utilsView.MyDate
import java.util.*

fun String.toHijriMonthName(lang: String): String =
    when(this){
        "01" -> if(lang == ARABIC) " ٱلْمُحَرَّم " else " al-Muḥarram "
        "02" -> if(lang == ARABIC) " صَفَر " else "Ṣafar "
        "03" -> if(lang == ARABIC) " رَبِيع ٱلْأَوَّل " else " Rabīʿ al-ʾAwwal "
        "04" -> if(lang == ARABIC) " رَبِيع ٱلثَّانِي " else " Rabīʿ ath-Thānī "
        "05" -> if(lang == ARABIC) " جُمَادَىٰ ٱلْأُولَ " else " Jumadā al-ʾŪlā "
        "06" -> if(lang == ARABIC) " جُمَادَىٰ ٱلثَّانِيَة " else " Jumādā ath-Thāniyah "
        "07" -> if(lang == ARABIC) " رَجَب " else " Rajab "
        "08" -> if(lang == ARABIC) " شَعْبَان " else " Shaʿbān "
        "09" -> if(lang == ARABIC) " رَمَضَان " else " Ramaḍān "
        "10" -> if(lang == ARABIC) " شَوَّال " else " Shawwāl "
        "11" -> if(lang == ARABIC) " ذُو ٱلْقَعْدَة " else " Ḏū al-Qaʿdah "
        "12" -> if(lang == ARABIC) " ذُو ٱلْحِجَّة " else " Ḏū al-Ḥijjah "
        else -> this
    }

fun Int.toPrayerName(resources: Resources, showAthan: Boolean = true): String {
    val string = when (this % 6) {
        0 -> "${resources.getString(R.string.athan)} ${resources.getString(R.string.isha)}"
        1 -> "${resources.getString(R.string.athan)} ${resources.getString(R.string.fajr)}"
        2 -> resources.getString(R.string.sunrise)
        3 -> "${resources.getString(R.string.athan)} ${resources.getString(R.string.dhuhr)}"
        4 -> "${resources.getString(R.string.athan)} ${resources.getString(R.string.asr)}"
        5 -> "${resources.getString(R.string.athan)} ${resources.getString(R.string.maghrib)}"
        else -> this.toString()
    }
    return if(!showAthan)
        string.removePrefix(resources.getString(R.string.athan)).trim()
    else
        string
}

fun Int.toPrayerIdName(): String =
    when(this % 6){
        0 -> ISHA
        1 -> FAJR
        2 -> SUNRISE
        3 -> DHUHR
        4 -> ASR
        5 -> MAGHRIB
        else -> this.toString()
    }

//fun Int.toPrayerImage(): Int =
//    when(this % 6){
//        0 -> R.drawable.ic_asha
//        1 -> R.drawable.ic_fagr
//        2 -> R.drawable.ic_sunrise
//        3 -> R.drawable.ic_dohr
//        4 -> R.drawable.ic_asr
//        5 -> R.drawable.ic_magreb
//        else -> R.drawable.ic_asr
//    }

fun Long.toPrayerTime(): String = MyDate.convertDateToDateString(Date(this), "hh:mm aaa", Locale(
     ARABIC
))

fun Long.toKhatmaStringDate(): String = MyDate.convertDateToDateString(Date(this), "dd MMMM yyyy", Locale(
    ARABIC))
fun Long.toKhatmaStringReminder(): String = MyDate.convertDateToDateString(Date(this), "hh:mm aaa", Locale(
    ARABIC))

fun Long.toKhatmaDate(difference: Int = 0): String {
    val date = Date(this)
    val calendar = Calendar.getInstance()
    calendar.time = date

    calendar.add(Calendar.DATE, difference * -1)

    return  MyDate.convertDateToDateString(calendar.time, "dd MMMM yyyy", Locale(
        ARABIC
    ))
}

fun Int.toJuzaaName(resources: Resources): String
    = try {
        resources.getString(
            resources.getIdentifier(
                "juzza$this",
                "string",
                MyApplication.instance.packageName
            )
        )
    }catch (ex: Resources.NotFoundException){
        this.toString()
    }

fun Int.toSurahName(lang: String = ARABIC, showSurah: Boolean = lang == ARABIC, showTranslation: Boolean = false): String {
    var name = when (this) {
        1 -> if (lang == ARABIC) "الفَاتِحَة" else "Al-Fatihah\n(the Opening)"
        2 -> if (lang == ARABIC) "البَقَرَة" else "Al-Baqarah\n(the Cow)"
        3 -> if (lang == ARABIC) "آل عِمرَان" else "Aali Imran\n(the Family of Imran)"
        4 -> if (lang == ARABIC) "النِّسَاء" else "An-Nisa’\n(the Women)"
        5 -> if (lang == ARABIC) "المَائدة" else "Al-Ma’idah\n(the Table)"
        6 -> if (lang == ARABIC) "الأنعَام" else "Al-An’am\n(the Cattle)"
        7 -> if (lang == ARABIC) "الأعرَاف" else " Al-A’raf\n(the Heights)"
        8 -> if (lang == ARABIC) "الأنفَال" else "Al-Anfal\n(the Spoils of War)"
        9 -> if (lang == ARABIC) "التوبَة" else "At-Taubah\n(the Repentance)"
        10 -> if (lang == ARABIC) "يُونس" else " Yunus\n(Yunus)"
        11 -> if (lang == ARABIC) "هُود" else "Hud\n(Hud)"
        12 -> if (lang == ARABIC) "يُوسُف" else "Yusuf\n(Yusuf)"
        13 -> if (lang == ARABIC) "الرَّعْد" else "Ar-Ra’d\n(the Thunder)"
        14 -> if (lang == ARABIC) "إبراهِيم" else "Ibrahim\n(Ibrahim)"
        15 -> if (lang == ARABIC) "الحِجْر" else "Al-Hijr\n(the Rocky Tract)"
        16 -> if (lang == ARABIC) "النَّحْل" else "An-Nahl\n(the Bees)"
        17 -> if (lang == ARABIC) "الإسْرَاء" else "Al-Isra’\n(the Night Journey)"
        18 -> if (lang == ARABIC) "الكهْف" else "Al-Kahf\n(the Cave)"
        19 -> if (lang == ARABIC) "مَريَم" else "Maryam\n(Maryam)"
        20 -> if (lang == ARABIC) "طه" else "Ta-Ha\n(Ta-Ha)"
        21 -> if (lang == ARABIC) "الأنبيَاء" else "Al-Anbiya’\n(the Prophets)"
        22 -> if (lang == ARABIC) "الحَج" else "Al-Haj\n(the Pilgrimage)"
        23 -> if (lang == ARABIC) "المُؤمنون" else "Al-Mu’minun\n(the Believers)"
        24 -> if (lang == ARABIC) "النُّور" else "An-Nur\n(the Light)"
        25 -> if (lang == ARABIC) "الفُرْقان" else "Al-Furqan\n(the Criterion)"
        26 -> if (lang == ARABIC) "الشُّعَرَاء" else "Ash-Shu’ara’\n(the Poets)"
        27 -> if (lang == ARABIC) "النَّمْل" else "An-Naml\n(the Ants)"
        28 -> if (lang == ARABIC) "القَصَص" else "Al-Qasas\n(the Stories)"
        29 -> if (lang == ARABIC) "العَنكبوت" else "Al-Ankabut\n(the Spider)"
        30 -> if (lang == ARABIC) "الرُّوم" else "Ar-Rum\n(the Romans)"
        31 -> if (lang == ARABIC) "لقمَان" else "Luqman\n(Luqman)"
        32 -> if (lang == ARABIC) "السَّجدَة" else "As-Sajdah\n(the Prostration)"
        33 -> if (lang == ARABIC) "الأحزَاب" else "Al-Ahzab\n(the Combined Forces)"
        34 -> if (lang == ARABIC) "سَبَأ" else "Saba’\n(the Sabeans)"
        35 -> if (lang == ARABIC) "فَاطِر" else "Al-Fatir\n(the Originator)"
        36 -> if (lang == ARABIC) "يس" else "Ya-Sin\n(Ya-Sin)"
        37 -> if (lang == ARABIC) "الصَّافات" else "As-Saffah\n(Those Ranges in Ranks)"
        38 -> if (lang == ARABIC) "ص" else "Sad\n(Sad)"
        39 -> if (lang == ARABIC) "الزُّمَر" else "Az-Zumar\n(the Groups)"
        40 -> if (lang == ARABIC) "غَافِر" else "Ghafar\n(the Forgiver)"
        41 -> if (lang == ARABIC) "فُصِّلَتْ" else "Fusilat\n(Distinguished)"
        42 -> if (lang == ARABIC) "الشُّورَى" else "Ash-Shura\n(the Consultation)"
        43 -> if (lang == ARABIC) "الزُّخْرُف" else "Az-Zukhruf\n(the Gold)"
        44 -> if (lang == ARABIC) "الدُّخان" else "Ad-Dukhan\n(the Smoke)"
        45 -> if (lang == ARABIC) "الجاثِية" else "Al-Jathiyah\n(the Kneeling)"
        46 -> if (lang == ARABIC) "الأحقاف" else "Al-Ahqaf\n(the Valley)"
        47 -> if (lang == ARABIC) "مُحَمّد" else "Muhammad\n(Muhammad)"
        48 -> if (lang == ARABIC) "الفَتْح" else "Al-Fat’h\n(the Victory)"
        49 -> if (lang == ARABIC) "الحُجُرات" else "Al-Hujurat\n(the Dwellings)"
        50 -> if (lang == ARABIC) "ق" else "Qaf\n(Qaf)"
        51 -> if (lang == ARABIC) "الذَّاريَات" else "Adz-Dzariyah\n(the Scatterers)"
        52 -> if (lang == ARABIC) "الطُّور" else "At-Tur\n(the Mount)"
        53 -> if (lang == ARABIC) "النَّجْم" else "An-Najm\n(the Star)"
        54 -> if (lang == ARABIC) "القَمَر" else "Al-Qamar\n(the Moon)"
        55 -> if (lang == ARABIC) "الرَّحمن" else "Ar-Rahman\n(the Most Gracious)"
        56 -> if (lang == ARABIC) "الواقِعَة" else "Al-Waqi’ah\n(the Event)"
        57 -> if (lang == ARABIC) "الحَديد" else "Al-Hadid\n(the Iron)"
        58 -> if (lang == ARABIC) "المُجادَلة" else "Al-Mujadilah\n(the Reasoning)"
        59 -> if (lang == ARABIC) "الحَشْر" else "Al-Hashr\n(the Gathering)"
        60 -> if (lang == ARABIC) "المُمتَحَنة" else "Al-Mumtahanah\n(the Tested)"
        61 -> if (lang == ARABIC) "الصَّف" else "As-Saf\n(the Row)"
        62 -> if (lang == ARABIC) "الجُّمُعة" else "Al-Jum’ah\n(Friday)"
        63 -> if (lang == ARABIC) "المُنافِقُون" else "Al-Munafiqun\n(the Hypocrites)"
        64 -> if (lang == ARABIC) "التَّغابُن" else "At-Taghabun\n(the Loss & Gain)"
        65 -> if (lang == ARABIC) "الطَّلاق" else "At-Talaq\n(the Divorce)"
        66 -> if (lang == ARABIC) "التَّحْريم" else "At-Tahrim\n(the Prohibition)"
        67 -> if (lang == ARABIC) "المُلْك" else "Al-Mulk –\n(the Kingdom)"
        68 -> if (lang == ARABIC) "القَلـََم" else "Al-Qalam\n(the Pen)"
        69 -> if (lang == ARABIC) "الحَاقّـَة" else "Al-Haqqah\n(the Inevitable)"
        70 -> if (lang == ARABIC) "المَعارِج" else "Al-Ma’arij\n(the Elevated Passages)"
        71 -> if (lang == ARABIC) "نُوح" else "Nuh\n(Nuh)"
        72 -> if (lang == ARABIC) "الجِنّ" else "Al-Jinn\n(the Jinn)"
        73 -> if (lang == ARABIC) "المُزَّمّـِل" else "Al-Muzammil\n(the Wrapped)"
        74 -> if (lang == ARABIC) "المُدَّثــِّر" else "Al-Mudaththir\n(the Cloaked)"
        75 -> if (lang == ARABIC) "القِيامَة" else "Al-Qiyamah\n(the Resurrection)"
        76 -> if (lang == ARABIC) "الإنسان" else "Al-Insan\n(the Human)"
        77 -> if (lang == ARABIC) "المُرسَلات" else "Al-Mursalat\n(Those Sent Forth)"
        78 -> if (lang == ARABIC) "النـَّبأ" else "An-Naba’\n(the Great News)"
        79 -> if (lang == ARABIC) "النـّازِعات" else "An-Nazi’at\n(Those Who Pull Out)"
        80 -> if (lang == ARABIC) "عَبَس" else "‘Abasa\n(He Frowned)"
        81 -> if (lang == ARABIC) "التـَّكْوير" else "At-Takwir\n(the Overthrowing)"
        82 -> if (lang == ARABIC) "الإنفِطار" else "Al-Infitar\n(the Cleaving)"
        83 -> if (lang == ARABIC) "المُطـَفِّفين" else "Al-Mutaffifin\n(Those Who Deal in Fraud)"
        84 -> if (lang == ARABIC) "الإنشِقاق" else "Al-Inshiqaq\n(the Splitting Asunder)"
        85 -> if (lang == ARABIC) "البُروج" else "Al-Buruj\n(the Stars)"
        86 -> if (lang == ARABIC) "الطّارق" else "At-Tariq\n(the Nightcomer)"
        87 -> if (lang == ARABIC) "الأعلی" else "Al-A’la\n(the Most High)"
        88 -> if (lang == ARABIC) "الغاشِيَة" else "Al-Ghashiyah\n(the Overwhelming)"
        89 -> if (lang == ARABIC) "الفَجْر" else "Al-Fajr\n(the Dawn)"
        90 -> if (lang == ARABIC) "البَـلـَد" else "Al-Balad\n(the City)"
        91 -> if (lang == ARABIC) "الشــَّمْس" else "Ash-Shams\n(the Sun)"
        92 -> if (lang == ARABIC) "اللـَّيل" else "Al-Layl\n(the Night)"
        93 -> if (lang == ARABIC) "الضُّحی" else "Adh-Dhuha\n(the Forenoon)"
        94 -> if (lang == ARABIC) "الشَّرْح" else "Al-Inshirah\n(the Opening Forth)"
        95 -> if (lang == ARABIC) "التـِّين" else "At-Tin\n(the Fig)"
        96 -> if (lang == ARABIC) "العَلـَق" else "Al-‘Alaq\n(the Clot)"
        97 -> if (lang == ARABIC) "القـَدر" else "Al-Qadar\n(the Night of Decree)"
        98 -> if (lang == ARABIC) "البَيِّنَة" else "Al-Bayinah\n(the Proof)"
        99 -> if (lang == ARABIC) "الزلزَلة" else "Az-Zalzalah\n(the Earthquake)"
        100 -> if (lang == ARABIC) "العَادِيات" else "Al-‘Adiyah\n(the Runners)"
        101 -> if (lang == ARABIC) "القارِعَة" else "Al-Qari’ah\n(the Striking Hour)"
        102 -> if (lang == ARABIC) "التَكاثـُر" else "At-Takathur\n(the Piling Up)"
        103 -> if (lang == ARABIC) "العَصْر" else "Al-‘Asr\n(the Time)"
        104 -> if (lang == ARABIC) "الهُمَزَة" else "Al-Humazah\n(the Slanderer)"
        105 -> if (lang == ARABIC) "الفِيل" else "Al-Fil\n(the Elephant)"
        106 -> if (lang == ARABIC) "قـُرَيْش" else "Quraish\n(Quraish)"
        107 -> if (lang == ARABIC) "المَاعُون" else "Al-Ma’un\n(the Assistance)"
        108 -> if (lang == ARABIC) "الكَوْثَر" else "Al-Kauthar\n(the River of Abundance)"
        109 -> if (lang == ARABIC) "الكَافِرُون" else "Al-Kafirun\n(the Disbelievers)"
        110 -> if (lang == ARABIC) "النـَّصر" else "An-Nasr\n(the Help)"
        111 -> if (lang == ARABIC) "المَسَد" else "Al-Masad\n(the Palm Fiber)"
        112 -> if (lang == ARABIC) "الإخْلَاص" else "Al-Ikhlas\n(the Sincerity)"
        113 -> if (lang == ARABIC) "الفَلَق" else "Al-Falaq\n(the Daybreak)"
        114 -> if (lang == ARABIC) "النَّاس" else "An-Nas\n(Mankind)"
        else -> ""
    }
    if(!showTranslation)
        name = name.split("\n")[0]
    return if(showSurah)
        "سورة $name"
    else
        name
}

fun Int.toHezbName(quarterNumber: Int, resources: Resources): String = try {
    val mod = quarterNumber % 4
    resources.getString(
        resources.getIdentifier(
            "hezb_part$this",
            "string",
            MyApplication.instance.packageName
        )
    ) + " ${if(mod == 0) quarterNumber / 4 else (quarterNumber - mod) / 4 + 1}"
} catch (ex: Resources.NotFoundException){
    this.toString()
}


