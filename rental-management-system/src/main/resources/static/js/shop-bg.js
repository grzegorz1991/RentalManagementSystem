const shopBackgrounds = document.querySelectorAll('.shop-bg');
const imageUrls = [
    'url(../img/custom-graphics/Shop/Backgrounds/Shop-bg1.jpeg)',
    'url(../img/custom-graphics/Shop/Backgrounds/Shop-bg2.jpeg)',
    'url(../img/custom-graphics/Shop/Backgrounds/Shop-bg3.jpeg)',
];

shopBackgrounds.forEach(background => {
    const randomIndex = Math.floor(Math.random() * imageUrls.length);
    background.style.backgroundImage = imageUrls[randomIndex];
    background.style.backgroundSize = 'cover';
    background.style.backgroundPosition = 'center';
    background.style.backgroundAttachment = 'fixed';
});