const indexUserBtn = document.getElementById('indexUserBtn');
const indexAdminBtn = document.getElementById('indexAdminBtn');
alert('hello');

indexUserBtn.addEventListener('click', () => location.pathname = '/user');

indexAdminBtn.addEventListener('click', () => {
    console.log(window.location.pathname);
    window.location.pathname = '/admin';
});


