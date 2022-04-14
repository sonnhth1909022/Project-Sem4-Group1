<template>
  <div class="container"> 
      <div class=" text-center mt-5 ">
        <h1>Direction Page</h1>
      </div>
    <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <h1>Search</h1>
                        <router-link to="/moviecategory">
                        <p>1. Search Movie By Category</p>
                        </router-link>

                         <router-link to="/moviecast">
                        <p>2. Search Movie By Cast</p>
                        </router-link>

                         <router-link to="/moviedirector">
                        <p>3. Search Movie By Director</p>
                        </router-link>

                    </div>
                </div>
            </div> <!-- /.8 -->
        </div> <!-- /.row-->
    </div>
</div>
</template>

<script>
export default {

}
</script>
<style scoped>
body {
    font-family: 'Lato', sans-serif
}

h1 {
    margin-bottom: 40px
}

label {
    color: #333
}

.btn-send {
    font-weight: 300;
    text-transform: uppercase;
    letter-spacing: 0.2em;
    width: 80%;
    margin-left: 3px
}

.help-block.with-errors {
    color: #ff5050;
    margin-top: 5px
}

.card {
    margin-left: 10px;
    margin-right: 10px
}
</style>