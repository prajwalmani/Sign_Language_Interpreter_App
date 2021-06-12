const form = document.forms[0];
form.addEventListener("submit", function(event) {
    event.preventDefault();
    document.getElementById("out").innerHTML = ""
    const { search } = this.elements;
    var s=search.value
    var author="@signwithrobert"
    var search_term=author.concat(" ",s)
    let apikey="DsCHXY2CPBGbdeWb6dJ1QFF1W0GWDr8A";
    let url=`https://api.giphy.com/v1/gifs/search?api_key=${apikey}&limit=1&q=`
    url=url.concat(search_term)
    console.log(url)
    fetch(url)
    .then(response => response.json())
    .then(content=>{
        let fig = document.createElement("figure");
        let img = document.createElement("img");
        let fc = document.createElement("figcaption");
        img.src = content.data[0].images.downsized.url;
        // img.alt = content.data[0].title;
        // fc.textContent = content.data[0].title;
        fig.appendChild(img);
        // fig.appendChild(fc);
        let out = document.querySelector(".out");
        out.insertAdjacentElement("afterbegin", fig);
        document.querySelector("#search").value = "";
    })
    .catch(err=>{
        document.getElementById("out").innerHTML = "<h2>Oops! We didn't find the sign for your search term</h2>"
    }
        )
  });