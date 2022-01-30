module.exports = {
    
    //API CONFIGURATION

    projectName: "n11 TalentHub Java Spring Bootcamp Bitirme Projesi",
    projectInfo: "Bir kredi başvuru sistemi için, kredi başvuru isteklerini alıp ilgili kriterlere göre müşteriye kredi sonucunu gösteren uygulama",
    projectFunctionalitiesInfo: [
        "Kredi Başvurusu yapmak için => Yeni Kullanıcı Kredi Başvurusu",
        "Başvuru Sonucunu Görüntülemek için => Başvuru Sonucum",
        "Onaylanmamış Kredi Başvurusuna sahip kullacılar için bilgi güncelleyip yeni başvuru yapmak için => Bilgilerimi Güncelle ve Yeni Başvuru"
    ],

    // NAME CONFIGURATIONS
    homePage: "Anasayfa",
    firstPage: "Yeni Kullanıcı Kredi Başvurusu",
    secondPage: "Başvuru Sonucum",
    thirdPage: "Bilgilerimi Güncelle ve Yeni Başvuru",
    applyButton: "Başvuru Yap",

    //LOAN APPLICATON FIELDS
    loanApplicationFields: [
        {tr:"Kimlik numarası", eng: "ssn"}, 
        {tr:"ad-soyad", eng:"fullName"}, 
        {tr:"telefon", eng:"mobileNumber"}, 
        {tr:"doğum tarihi", eng:"birthdate"} , 
        {tr:"aylık gelir", eng:"income"}, 
        {tr:"teminat", eng:"deposit"}
    ],

    //UPDATE ACTION FIELDS
    updateActionFields:[
        {tr:"Kimlik numarası", eng: "ssn"}, 
        {tr:"doğum tarihi", eng:"birthdate"} , 
        {tr:"aylık gelir", eng:"income"}, 
        {tr:"teminat", eng:"deposit"},
        {tr:"telefon", eng:"mobileNumber"} 
    ],

    //UPDATE ACTION FIELDS
    showResultActionFields:[
        {tr:"Kimlik numarası", eng: "ssn"}, 
        {tr:"doğum tarihi", eng:"birthdate"} , 
    ]
    
}