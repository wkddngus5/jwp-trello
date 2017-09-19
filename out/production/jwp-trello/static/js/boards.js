var BOARDS = (function (window) {
  let header = $("meta[name='_csrf_header']").attr("content");
  let token = $("meta[name='_csrf']").attr("content");

  'use strict';

  function init() {

    $("#modal").modal();
    $(".board-list").on("click", ".board", function(event) {
      const boardId = event.target.getAttribute('data-item');
      window.location.href = ("board/" + boardId);
    });
    $(".add-board-btn").on("click", showCreateBoardForm);
    $(".add-board-form .save").on("click", createNewBoard);
    $(".close-moadl").on("click", closeModal);
  }

  function showCreateBoardForm() {

    $('#modal').modal('open');

  }

  function createNewBoard() {
    var boardName = $(".board-name").val();

    if (boardName == "") {
      $(".warning").css("display", "block");
      return;
    }

    let jsonData = JSON.stringify({
      "title": boardName
    });

    $.ajax({
      type: "post",
      url: "/api/boards",
      data: jsonData,
      beforeSend: (xhr) => {
        xhr.setRequestHeader(header, token);
      },
      contentType: "application/json",
      success: (res) => {
        if (res.length === 0) {
          window.location.href = "/login";
        } else {
          console.log('response: ', res);
          $(".warning").css("display", "none");
          var str = Template.board.replace(/\{\{input-value\}\}/gi, boardName);
          var str = str.replace(/\{\{id\}\}/gi, res.id);
          console.log('str: ' + str);
          $(".board-name").val("");
          $("#modal").modal("close");
          console.log($(".board-list").append(str));
        }
      }
    });
  }

  function gotoBoard(event) {
    // console.log(event);

    // window.location.href = ("boards/");

  }

  function closeModal() {

    $("#modal").modal("close");

  }


  return {
    "init": init
  }
})(window);

$(function () {
  BOARDS.init();
});
