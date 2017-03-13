function showAll(beastId, userName) {
        console.log(userName);
        $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:8080/comment/showAll/"+beastId.toString(),
        async: true,
        success: function (dataJson) {
            createDivsFromJson(dataJson, userName);
        }
    });
}

function createDivsFromJson(dataJson, userName){
    for(var i=0; i<dataJson.length; ++i){
        createDiv(dataJson[i], userName);
    }
}

function createDiv(dataJson, userName) {
    var commentDiv = document.createElement('div');
    commentDiv.id = "commDiv" + dataJson.id;
    commentDiv.className = "jumbotron";
    commentDiv.style = "background-color: rgba(136, 191, 211, 0.6);";
    commentDiv.append(dataJson.content);

    createButtons(dataJson, userName);
    $('#commentsZone').append(commentDiv);
}

function createButtons(dataJson, userName) {
        //jesli button nalezy do zalogowanej osoby lub jesli to jest administrator
    if(userName==dataJson.userLogin || userName == 'a') {
        var delButton = document.createElement('button');
        delButton.id = "delBut" + dataJson.id;
        delButton.onclick = function () {
            deleteComment(dataJson.id);
        };
        delButton.style = "float:right;";
        delButton.className = "btn btn-danger";
        delButton.append("Delete");
        $('#commentsZone').append(delButton);

        var editButton = document.createElement('button');
        editButton.id = "editBut" + dataJson.id;
        editButton.onclick = function () {
            editComment(dataJson);
        }
        editButton.style = "float:right;";
        editButton.className = "btn btn-primar";
        editButton.append("Edit");
        $('#commentsZone').append(editButton);
    }
}

function sendComment(beastId, userName){
    var bla = $('#commentInput').val();
    console.log("request od usera: " + userName);
    //trzeba bedzie zmienic uzytkownika od ktorego dostaje requesta
    var json = {"userLogin" : userName, "beastId" : beastId, "content" : bla};
    $.ajax({
        url: "http://localhost:8080/comment/add",
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        dataType: 'json',
        success: function(dataJson){
            console.log(dataJson);
            createDiv(dataJson, 'a');
        }
    });
    //aby nie wysylac kilku komentarzy pod rzad
    $('#commentInput').attr("disabled", true);
    $('#commentInputButton').attr("disabled", true);
}

function deleteComment(commentId) {
    $.ajax({
        url : "http://localhost:8080/comment/delete/" + commentId.toString(),
        type : "DELETE",
        contentType: "application/json; charset=utf-8",
        success: function () {
            var div = document.getElementById("commDiv"+commentId.toString());
            var butEdit = document.getElementById("editBut"+commentId.toString());
            var butDel = document.getElementById("delBut"+commentId.toString());
            div.parentNode.removeChild(div);
            butDel.parentNode.removeChild(butDel);
            butEdit.parentNode.removeChild(butEdit);
            console.log("the object was deleted");
        }
    });
}

function editComment(commentJson) {
        //dostajemy komentarz w formacie json
    var editedDiv = document.getElementById("commDiv" + (commentJson.id).toString());
        //czyscimy zawartosc komentarza
    $(editedDiv).empty();
        //dodajemy pole do wysywalania
    var inputField = document.createElement("input");
    inputField.className = "form-control";
    inputField.id = "temporaryInput";
    inputField.value = commentJson.content;
    $(editedDiv).append(inputField);
        //dodajemy przycisk edycji
    var buttonEditField = document.createElement("button");
    buttonEditField.id = "temporaryEditButton";
    buttonEditField.className = "btn btn-primar";
    buttonEditField.onclick = function () {
        editField($(inputField).val(), commentJson, editedDiv);
    }
    $(buttonEditField).append("Edit");
    $(editedDiv).append(buttonEditField);
}

    //uzyta po zatwierdzeniu nowego komentarza
    //przesylam tresc nowego komentarza pobrana z inputa, stary komentraz w json
    //oraz diva do ktorego zapisany jest komentarz
function editField(newCommentContent, commentJson, editedDiv){
    //usuwamy przycisk i input'a
    $(editedDiv).empty();
            //wysylamy put ajaxem, nowy obiekt json
    var json = {"id" : commentJson.id, "userLogin" : commentJson.userLogin, "beastId" : commentJson.beastId, "content" : newCommentContent};
    $.ajax({
       url : "http://localhost:8080/comment/edit",
        type : "PUT",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        putData : json,
        success: function(dataJson){
            $(editedDiv).append(dataJson.content);
            //createDiv(dataJson);
        }
    });
}



