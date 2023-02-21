(() => {
  const btn1 = document.querySelector('#btn1');
  const username = document.querySelector('#username');
  const nickname = document.querySelector('#nickname');
  const oPassword = document.querySelector('#oPassword');
  const lastUpdatedDate = document.querySelector('#lastUpdatedDate');
  init();

  function init() {
    btn1.addEventListener('click', edit);

    fetch('edit')
      .then(resp => resp.json())
      .then(body => {
      	lastUpdatedDate.textContent = body['lastUpdatedDate'];
        username.value = body['username'];
        nickname.value = body['nickname'];
      });

    oPassword.addEventListener('blur', checkOldPassword);
  }

  function checkOldPassword() {
    fetch(`edit/${oPassword.value}`)
      .then(resp => resp.json())
      .then(body => {
        btn1.disabled = !body['successful']
      });
  }

  const msg = document.querySelector('#msg');
  const nPassword = document.querySelector('#nPassword');
  const confirmPassword = document.querySelector('#confirmPassword');
  const currentUser = document.querySelector('#currentUser');

  function edit() {
    if (nPassword.value && confirmPassword.value) {
      if (nPassword.value.length < 6 || nPassword.value.length > 12) {
        msg.textContent = '密碼長度須介於6~12字元';
        return;
      }

      if (confirmPassword.value !== nPassword.value) {
        msg.textContent = '密碼與確認密碼不相符';
        return;
      }
    }

    const nicknameLength = nickname.value.length;
    if (nicknameLength < 1 || nicknameLength > 20) {
      msg.textContent = '暱稱長度須介於1~20字元';
      return;
    }

    msg.textContent = '';

    fetch('edit', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        password: nPassword.value,
        nickname: nickname.value
      }),
    })
      .then(resp => resp.json())
      .then(body => {
        const { successful, message, nickname: nicknameValue } = body;
        if (successful) {
          msg.className = 'info';
          sessionStorage.setItem('nickname', nicknameValue);
          currentUser.textContent = nicknameValue;
          oPassword.value = '';
          nPassword.value = '';
          confirmPassword.value = '';
          nickname.value = nicknameValue;
          btn1.disabled = true;
        } else {
          msg.className = 'error';
        }
        msg.textContent = message;
      });
  }
})();