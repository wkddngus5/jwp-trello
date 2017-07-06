/**
 * Created by Naver on 2017. 7. 4..
 */

class Login {
  constructor() {
    this.ENTERKEY_CODE = 13;
    this.HTTP_STATUS_OK = "success";

    document.querySelector("#password").addEventListener("keypress", e => {
      if(e.which === this.ENTERKEY_CODE) {
        if(document.querySelector("#password").value === "") {
          alert("비밀번호를 입력해주세요!");
          e.preventDefault();
          return;
        }
        this.postLogin(e);
      }
    });

    document.querySelector(".login-btn").addEventListener("click", e => {
      this.postLogin(e);
    })
  }

  postLogin(e) {
    e.preventDefault();

    let jsonData = JSON.stringify({
      "userId": document.querySelector("#id").value,
      "password": document.querySelector("#password").value
    });

    console.log("USER: ", jsonData);

    $.ajax({
      type: "post",
      url: "/api/login",
      data: jsonData,
      contentType: "application/json",
      success: this.onSuccess,
      error: this.onError
    });
  }

  onSuccess(status) {
    window.location.href = "/";
  }

  onError(status) {
    alert(status.responseText);
  }
}

new Login();