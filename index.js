const registerLink = document.getElementById("register-link");
const logregBox = document.querySelector(".logreg-box");
const loginLink = document.querySelector(".login-link");
const signIn = document.getElementById("sign-in");
const signUp = document.getElementById("signUp");

// swap between sign-in to sign-up form
registerLink.addEventListener('click', () => {
    logregBox.classList.add('active');
    registerLink.style.display = "none";
    loginLink.style.display = "flex";
});
loginLink.addEventListener('click', () => {
    logregBox.classList.remove('active');
    loginLink.style.display = "none";
    registerLink.style.display = "flex";
});

// Test Rest API
async function checkApi() {
    const response = await fetch(`https://13.50.48.24:8080/user/check`);
    const data = await response.json();
    console.log(data);
}
checkApi();
//Login user function 
signIn.addEventListener("click", async (event) => {
    event.preventDefault();

    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    axios.get("https://13.50.48.24:8080/user/login", {
        params: {
            userEmail: email,
            userPassword: password
        }
    })
        .then(res => {
            const responseData = res.data;
    console.log(responseData);
        })
        .catch(err => {
            if (err.response && err.response.data) {
                const errResponseData = err.response.data;
                
                if(errResponseData.emailId === email){
                    alert(`Welcome ${err.response.data.userName}, You have succefully Logged In`);    
                }else if(errResponseData === "Invalid Password!"){
                    alert("Invalid Password! Try Again with Correct password")
                }else if(errResponseData === `User not found! Enter valid email Id.`){
                    alert(errResponseData);
                }else{
                    alert ("Server is Down! Try Again after some time")
                }
                console.log(errResponseData);
              } else {
                console.log(err);
                alert("Server is Down! Try Again after some time")
              }
        });
    });

//signUp / Register User Function
signUp.addEventListener("click", async (event) => {
    event.preventDefault();

    const name = document.getElementById("regiter-name").value;
    const email = document.getElementById("regiter-email").value;
    const password = document.getElementById("regiter-password").value;

    axios.post("https://13.50.48.24:8080/user/register", {
        userName: name,
        userEmail: email,
        userPassword: password
    }, {
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res => {
            if(res.data.emailId === email){
                alert("You have been Succefully registered in Our Website, Now Try Sign In");
            }
            console.log(res.data)})
        .catch(err => console.log(err));
});
