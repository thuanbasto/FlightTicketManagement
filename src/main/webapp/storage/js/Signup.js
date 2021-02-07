// password
const password = document.querySelector("#password");
const confirmPassword = document.querySelector("#confirmPassword");
const passwordErrorMatch = document.querySelector("#passwordErrorMatch");
const passwordErrors = document.querySelector("#password\\.errors")

// error lable
const username = document.querySelector("#username");
const usernameErrorLength = document.querySelector("#usernameErrorLength");
const usernameErrorRegex = document.querySelector("#usernameErrorRegex");
const usernameErrorExist = document.querySelector("#usernameErrorExist");
const usernameErrors = document.querySelector("#username\\.errors");

// submit button
const submit = document.querySelector("#submit");
const next = document.querySelector("#next");
const back = document.querySelector("#back");

// submit field
const usernameDiv = document.querySelector(".username");
const passwordDiv = document.querySelector(".password");
const confirmPasswordDiv = document.querySelector(".confirmPassword");
const fullname = document.querySelector(".name");
const birthDay = document.querySelector(".birthDay");
const phone = document.querySelector(".phone");
const address = document.querySelector(".address");
const email = document.querySelector(".email");
const role = document.querySelector(".role");
const enable = document.querySelector(".enable");

// start page
next.disabled = true;
submit.disabled = true;
submit.style.display = "none";
back.style.display = "none";
usernameErrorLength.style.display = "none";
usernameErrorRegex.style.display = "none";
passwordErrorMatch.style.display = "none";
usernameErrorExist.style.display = "none";

fullname.style.display = "none";
birthDay.style.display = "none";
phone.style.display = "none";
address.style.display = "none";
email.style.display = "none";
role.style.display = "none";
enable.style.display = "none";

// set "" for 2 password field 
password.value = "";
confirmPassword.value = "";

// variable count the button next
var section = 1;

async function checkExistUsername(username){
    await $.ajax({
        method: "GET",
        contentType: "application/json; charset=utf-8",
        url: "/FlightTicketManagement/admin/user/" + username,
        dataType: "json",
        success:function(data){
            if (data) {
                usernameErrorExist.style.display = "";
                // return true; // username is exist
            } else {
                usernameErrorExist.style.display = "none";
                // return false; // username isn't exist
            }
        }
    })
    enableNext();
}

function enableSubmit(){
    if (section === 3){
        if ($("#email").val() != "")
            submit.disabled = false;
        else
            submit.disabled = true;
        
    }
}

// enable next button when all field aren't null
function enableNext(){
    if (section === 1){
        if (usernameErrorLength.style.display == "none" 
            && usernameErrorRegex.style.display == "none" 
                && passwordErrorMatch.style.display == "none"
                    && usernameErrorExist.style.display == "none"
                        && password.value != "" && confirmPassword.value != "" && username.value != ""){
            next.disabled = false;
        } else {
            next.disabled = true;
        }
    } else if (section === 2){
        if ($("#firstName").val() != "" && $("#lastName").val() != "" && $("#birthDay").val() != "" 
            && $("#phone").val() != "" && $("#address").val() != ""){
            next.disabled = false;
        } else {
            next.disabled = true;
        }
    }
}

next.addEventListener("click", () =>{
    event.preventDefault();

    if (section === 1) {

        usernameDiv.style.display = "none";
        passwordDiv.style.display = "none";
        confirmPasswordDiv.style.display = "none";

        fullname.style.display = "";
        birthDay.style.display = "";
        phone.style.display = "";
        address.style.display = "";

        // back button appearance when section > 1
        back.style.display = "";

        section++;

    } else if (section === 2) {
        fullname.style.display = "none";
        birthDay.style.display = "none";
        phone.style.display = "none";
        address.style.display = "none";
        
        email.style.display = "";
        role.style.display = "";
        enable.style.display = "";

        // section == 3 => hide next button and submit button appearance
        next.style.display = "none";
        submit.style.display = "";
        
        section++;
    }
    enableNext();
    enableSubmit();
})

back.addEventListener("click", () =>{
    event.preventDefault();
    if (section === 2) {
        usernameDiv.style.display = "";
        passwordDiv.style.display = "";
        confirmPasswordDiv.style.display = "";

        fullname.style.display = "none";
        birthDay.style.display = "none";
        phone.style.display = "none";
        address.style.display = "none";

        // section == 1 => hide back button and sumit button
        back.style.display = "none";
        submit.style.display = "none";
        next.style.display = "";

        section--;
    } else if (section === 3) {
        fullname.style.display = "";
        birthDay.style.display = "";
        phone.style.display = "";
        address.style.display = "";
        
        email.style.display = "none";
        role.style.display = "none";
        enable.style.display = "none";

        // section < 3 => hide sumit button and next button appaerance
        submit.style.display = "none";
        next.style.display = "";

        section--;
    }
    enableNext();
})


// check username
username.addEventListener("focusout", () =>{
    if (usernameErrorLength.style.display == "none" 
        && usernameErrorRegex.style.display == "none" 
            && passwordErrorMatch.style.display == "none"
                && username.value != ""){
                    checkExistUsername(username.value); 
    }
})

username.addEventListener("keyup", () => {
    if (usernameErrors != null)
		usernameErrors.style.display = "none";
    next.disabled = true;

    var nameRegex = /^[a-zA-Z0-9]+$/;
    var validUsername = nameRegex.test(username.value);
    
    if (validUsername === false)
        usernameErrorRegex.style.display = "";
    else 
        usernameErrorRegex.style.display = "none";
    

    if (username.value.length < 6){
        usernameErrorLength.style.display = "";
        usernameErrorExist.style.display = "none";
    } else {
        usernameErrorLength.style.display = "none";
    }
})
// check password match or not match
password.addEventListener("keyup", () => {
    if (passwordErrors != null)
        passwordErrors.style.display = "none";
        
    if (confirmPassword.value !== password.value && confirmPassword.value !== "")
    	passwordErrorMatch.style.display = "";
    else 
    	passwordErrorMatch.style.display = "none";
    
    enableNext();
})
confirmPassword.addEventListener("keyup", () => {
    if (passwordErrors != null)
        passwordErrors.style.display = "none";
        
    if (confirmPassword.value !== password.value)
    	passwordErrorMatch.style.display = "";
    else
    	passwordErrorMatch.style.display = "none";
    
    
    enableNext();
})

$("#firstName").keyup(() => {
    enableNext();
})
$("#lastName").keyup(() => {
    enableNext();
})
$("#birthDay").change(() => {
    enableNext();
})
$("#address").keyup(() => {
    enableNext();
})
$("#phone").keyup(() => {
    enableNext();
})
$("#email").keyup(() => {
    enableSubmit();
})


