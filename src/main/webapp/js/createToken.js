function login(){
    let password = document.getElementById("Password").value;
    localStorage.setItem("password",password);
}