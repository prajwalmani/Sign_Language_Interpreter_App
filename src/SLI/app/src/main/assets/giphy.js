function spellcheckngify(word){
    let data="";
    data=data.concat("text=",word,"&language=en-US");
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            var response=this.responseText;
            var jsonres=JSON.parse(response);
            let out = document.querySelector(".out");
            let p=document.querySelector(".p")
            try{
                    const value =jsonres.matches[0].replacements[0].value;
                    const value1=jsonres.matches[0].replacements[1].value;
                    document.getElementById("out").innerHTML = ""
                    document.getElementById("out").setAttribute("style","width:393px");
                    var s=value
                    var author="@signwithrobert"
                    var search_term=author.concat(" ",s)
                    let apikey="DsCHXY2CPBGbdeWb6dJ1QFF1W0GWDr8A";
                    let url=`https://api.giphy.com/v1/gifs/search?api_key=${apikey}&limit=1&q=`
                    url=url.concat(search_term)
                    fetch(url)
                    .then(response => response.json())
                    .then(content=>{
                        let fig = document.createElement("figure");
                        let img = document.createElement("img");
                        let fc = document.createElement("figcaption");
                        img.src = content.data[0].images.downsized.url;
                        img.style.width='340px';
                        img.style.height='340px';
                        fig.appendChild(img);
                        let out = document.querySelector(".out");
                        out.insertAdjacentElement("afterbegin", fig);
                        let mainstring="<h2> You just made a typo,We have suggested you a sign! from this:"
                        document.getElementById("p").innerHTML =mainstring.concat("<u>",String(value),", ",String(value1),"</u>");
                        let ptag=document.getElementById("p");
                        ptag.style.marginLeft="40px";

                    })
                    .catch(err=>{
                        document.getElementById("out").innerHTML = "<h2 style='margin-left: 40px;'><span style='color:red'>Oops! We didn't find the sign for your search term</span></h2>"
                    }
                        )

            }
            catch(err){
                    document.getElementById("out").innerHTML = ""
                    document.getElementById("out").setAttribute("style","width:393px");
                    var s=word
                    var author="@signwithrobert"
                    var search_term=author.concat(" ",s)
                    let apikey="DsCHXY2CPBGbdeWb6dJ1QFF1W0GWDr8A";
                    let url=`https://api.giphy.com/v1/gifs/search?api_key=${apikey}&limit=1&q=`
                    url=url.concat(search_term)
                    fetch(url)
                    .then(response => response.json())
                    .then(content=>{
                        let fig = document.createElement("figure");
                        let img = document.createElement("img");
                        let fc = document.createElement("figcaption");
                        img.src = content.data[0].images.downsized.url;
                        img.style.width='340px';
                        img.style.height='340px';
                        fig.appendChild(img);
                        let out = document.querySelector(".out");
                        out.insertAdjacentElement("afterbegin", fig);
                    })
                    .catch(err=>{
                        document.getElementById("out").innerHTML = "<h2 style='margin-left: 40px;'><span style='color:red'>Oops! We didn't find the sign for your search term</span></h2>"
                    }
                        )

            }


        }
    });

    xhr.open("POST", "https://grammarbot.p.rapidapi.com/check");
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("x-rapidapi-key", "e2be155a48msha5f36a50e5a0239p192aefjsn5df12b82562e");
    xhr.setRequestHeader("x-rapidapi-host", "grammarbot.p.rapidapi.com");

    xhr.send(data);

}