const editForm = (formFields) => {
    let fieldCount = formFields.length;
    for (f=0; f < fieldCount; f++) {


        if (formFields[f].type == "button" || formFields[f].type == "submit" ) {
            if (formFields[f].classList.contains("d-none")) {
                formFields[f].classList.remove("d-none");
            }
            else {
                formFields[f].classList.add("d-none");
            }
        }
        else {
            if (formFields[f].disabled) {
                formFields[f].disabled = false;
            }
            else {
                formFields[f].disabled = true;
            }
        }


    }

}