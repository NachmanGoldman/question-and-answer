let elements = document.getElementsByClassName("display");
let showAnsr = function (id) {
    let i;
    fetch("http://localhost:8080/SendAnswers?id=" + id)
        .then(response => response.json())
        .then(jsonData => {
            if (jsonData.length === 0)
                return;
            document.getElementById("button" + id).innerText = 'Hide answers';
            for (i = 0; i < jsonData.length; i++)
                document.getElementById(id).innerHTML += `<p>${i + 1}. ${jsonData[i].answer}</p>`;
        })
    return;
};


let checkButton = function () {
    let id = this.getAttribute("data-pid");
    let btnCheck = document.getElementById("button" + id).innerText;
    let n = btnCheck.localeCompare("Show answers");
    if (n === 0) {
        showAnsr(id);
        return;
    }
    document.getElementById(id).innerHTML = "";
    document.getElementById("button" + id).innerText = 'Show answers';
    return;
}

for (let i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', checkButton);
}

//=========================================================
// validation for empty input field
//=========================================================
function validateForm() {
    let a = document.forms["myForm"]["name"].value;
    let b = document.forms["myForm"]["answer"].value;
    if (a == null || a == "" || b == null || b == "") {
        alert("Please Fill All Fields");
        return false;
    }
}