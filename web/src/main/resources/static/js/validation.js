document.getElementById('confirm').onkeyup = function () {
    let password = $("#password").val();
    let confirm_password = $("#confirm").val();
    if (password !== confirm_password) {
        $("#confirm").removeClass("is-valid").addClass("is-invalid");
        $("#confirmFeedback").text("Password does not match").removeClass("valid-feedback").addClass("invalid-feedback");
    } else {
        $("#confirm").removeClass("is-invalid").addClass("is-valid");
        $("#confirmFeedback").text("Password match").removeClass("invalid-feedback").addClass("valid-feedback");
    }
}