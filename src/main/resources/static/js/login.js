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

    // document.querySelector(".login-btn").addEventListener("click", e => {
    //   e.preventDefault();
    //   this.postLogin(e);
    // });
  }

  postLogin(e) {
    e.preventDefault();

    let jsonData = JSON.stringify({
      "userId": document.querySelector("#id").value,
      "password": document.querySelector("#password").value
    });

    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");

    console.log("USER: ", jsonData);
    console.log(token, header);

    $.ajax({
      type: "post",
      url: "/api/login",
      data: jsonData,
      beforeSend: (xhr) => {
        xhr.setRequestHeader(header, token);
      },
      contentType: "application/json",
      success: this.onSuccess,
      error: this.onError
    });
  }

  onSuccess(status) {
    window.location.href = "/boards";
  }

  onError(status) {
    alert(status.responseText);
  }
}

new Login();
