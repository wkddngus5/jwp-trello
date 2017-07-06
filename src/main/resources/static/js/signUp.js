class SignUp {
  constructor() {
    this.ENTERKEY_CODE = 13;

    document.querySelector("#password").addEventListener("keypress", e => {
      if(e.which === this.ENTERKEY_CODE) {
        if(document.querySelector("#password").value === "") {
          alert("비밀번호를 입력해주세요!");
          e.preventDefault();
          return;
        }
        this.postSignUp(e);
      }
    });

    document.querySelector(".signup-btn").addEventListener("click", e => {
      this.postSignUp(e);
    });
  }

  postSignUp(e) {
    e.preventDefault();

    let jsonData = JSON.stringify({
      "userId": document.querySelector("#userId").value,
      "email": document.querySelector("#email").value,
      "password": document.querySelector("#password").value
    });

    console.log("USER: ", jsonData);

    $.ajax({
      type: "post",
      url: "/api/users",
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
    alert(status.statusText);
  }
}

new SignUp();