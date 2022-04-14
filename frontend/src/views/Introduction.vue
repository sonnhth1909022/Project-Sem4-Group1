<template>
<div class="container">
<div class="about-section">
  <h1><b>About Us</b></h1>
</div>

<h2 style="text-align:center">Our Team</h2>
<div class="row">
  <div class="column">
    <div class="card">
      <img :src =" 'http://localhost:8080/images/Dax Shepard.jpg' " alt="Jane" class="previewImage size" >
      <div class="container">
        <h2>Jane Doe</h2>
        <p class="title">CEO & Founder</p>
        <p>Some text that describes me lorem ipsum ipsum lorem.</p>
        <p>jane@example.com</p>
        <p><button class="button">Contact</button></p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <img :src =" 'http://localhost:8080/images/Jonah Bobo.jpg' " alt="Mike" class="previewImage size" >
      <div class="container">
        <h2>Mike Ross</h2>
        <p class="title">Art Director</p>
        <p>Some text that describes me lorem ipsum ipsum lorem.</p>
        <p>mike@example.com</p>
        <p><button class="button">Contact</button></p>
      </div>
    </div>
  </div>
  
  <div class="column">
    <div class="card">
      <img :src =" 'http://localhost:8080/images/Josh Hutcherson.jpg' " alt="John" class="previewImage size" >
      <div class="container">
        <h2>John Doe</h2>
        <p class="title">Designer</p>
        <p>Some text that describes me lorem ipsum ipsum lorem.</p>
        <p>john@example.com</p>
        <p><button class="button">Contact</button></p>
      </div>
    </div>
  </div>
</div>

<div class="row">

    <h2 class="textmobile">Visit Our Mobile Application</h2>

    <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <form id="contact-form" role="form">
                                <div class="row">
                                   <p>To view movies using mobile ,visit this link to download the application</p>
                                   <a href="https://play.google.com/store/apps/details?id=com.movieapp.movienavigation&ah=TF37jzPoVzZgYqCqnk0NI7VpMk0"><p>Click here</p></a>
                                </div>
                        </form>
                    </div>
                </div>
            </div> <!-- /.8 -->
        </div> <!-- /.row-->

</div>
</div>

</template>
<script>
export default {
    name : "Introduction",


}
</script>

<style scoped>
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}
.textmobile
{
    text-align: center;
    margin-top:50px;
    margin-left:350px;
}
html {
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

.column {
  float: left;
  width: 33.3%;
  margin-bottom: 16px;
  padding: 0 8px;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin: 8px;
}

.about-section {
  padding: 50px;
  text-align: center;
  
  color: black;
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.button:hover {
  background-color: #555;
}

@media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}
.previewImage {
    height: 200px;
    width: 200px;
}
.size {
    max-width: 100%;
    max-height: 100%;
    margin-left:70px;
    margin-bottom: 10px;
    margin-top: 10px;
}

</style>