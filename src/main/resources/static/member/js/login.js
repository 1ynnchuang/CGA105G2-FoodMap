(() => {
    const username = document.querySelector('#username');
    const password = document.querySelector('#password');
    const errMsg = document.querySelector('#errMsg');
    const image = document.querySelector('#avatar');
    document.getElementById('btn1').addEventListener('click', () => {
        fetch(`login/${username.value}/${password.value}`)
            .then(resp => resp.json())
            .then(body => {
                errMsg.textContent = '';
                const {successful, message} = body;

                if (successful) {
                    // 回傳值在body中，用key抓出
                    const {id, nickname, roleId,image} = body;
                    sessionStorage.setItem('id', id);
                    sessionStorage.setItem('nickname', nickname);
                    sessionStorage.setItem('roleId', roleId);
                    sessionStorage.setItem('image', image);
                    //   跳轉網頁
                    location = '../index.html';
                } else {
                    errMsg.textContent = message;
                }
            });
    });
})();