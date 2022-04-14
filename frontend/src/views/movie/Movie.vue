<template>
  <div class="container"> 
      <div class=" text-center mt-5 ">
        <h3>Update a Movie</h3>
      </div>
    <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <div v-if = "currentMovie">
                    <form @submit.prevent="onSubmit">
                            <div class="form-group">
                            <label for="title" class="float-left">Name</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentMovie.name"
                                name = "title"
                            />
                            </div>
                            <div class="form-group">
                            <label for="description" class="float-left">Description</label>
                            <textarea  type="text"
                                class="form-control"
                                id="description"
                                required
                                v-model="currentMovie.description"
                                name = "description"
                                rows="4" cols="50"
                            />
                            </div>

                            <div class="form-group">
                            <label for="url" class="float-left">Url</label>
                            <textarea type="text"
                                class="form-control"
                                id="url"
                                required
                                v-model="currentMovie.url"
                                name = "url"
                            />
                            </div>

                            <div class="form-group">
                            <label for="duration" class="float-left">Duration</label>
                            <input type="number"
                                class="form-control"
                                id="duration"
                                required
                                v-model="currentMovie.duration"
                                name = "duration"
                            />
                            </div>



     <h4>Movie Type</h4>
            <div class="form-group">
            <multiselect
              v-model="currentMovie.movieType"
              :options="optionMovieType"
              :close-on-select="true"
              :clear-on-select="false"
              placeholder="Movie Type"
              label="name"
              track-by="id"
            ></multiselect>
            </div> 
         <h4>Categories</h4>
            <div class="form-group">
            <multiselect
              v-model="currentMovie.categories"
              :options="optionCategory"
              :multiple="true"
              :close-on-select="true"
              placeholder="Categories"
              label="name"
              track-by="id"
            ></multiselect>
            </div>
            
        <h4 >Casts</h4>
        <div class="form-group">
            <multiselect
              v-model="currentMovie.casts"
              :options="optionCast"
              :multiple="true"
              :close-on-select="true"
              placeholder="Casts"
              label="name"
              track-by="id"
            ></multiselect>
        </div>
        <h4 >Directors</h4>
        <div class="form-group">
            <multiselect
              v-model="currentMovie.directors"
              :options="optionDirector"
              :multiple="true"
              :close-on-select="true"
              placeholder="Directors"
              label="name"
              track-by="id"
            ></multiselect>
        </div>  
                            <div class="form-group">                       
                            <label for="image" class="float-left">Image</label>
                            <br />
                            <div v-if = "!submitted" class= "previewImage">
                            <img :src ="currentMovie.thumbnail" class="size float-left"/>
                            </div>
                            </div>

                            <div class = "form-group">
                            <div v-if="previewImage" class="previewImage">
                            <img class="preview my-3 float-left size" :src="previewImage" alt="" />  
                            <br />
                            </div>
                            </div>
                            <div class = "form-group">
                            <input type = "file" @change="onFileUpload" class="float-left">
                            </div>
                            <br />
                            <br />
                            <br />
                            <div class="form-group">
                            <button class="btn btn-primary btn-block btn-lg">Confirm</button>
                            </div>

                            <div class="form-group">
                                    <h3>{{message}}</h3>
                            </div>

                    </form>
                        </div>
                        <div v-else>
                         <h4>Please choose a movie</h4>
                        </div>
                </div>
            </div> 
        </div> 
    </div>
</div>
</div>
</template>

<script>
import axios from 'axios'
import authHeader from '../../services/auth-header';
import Multiselect from '@suadelabs/vue3-multiselect';
import MovieService from '../../services/movie/movie.service'
export default {
    name : "update-movie",
    components: { Multiselect },
    data() {
    return {
      FILE: null,
      currentMovie : null,
      submitted : false,
      previewImage: undefined,
      message : '',
      optionCategory: [],
      optionDirector: [],
      optionCast: [],
      optionMovieType: [],
    };
    },
     methods: {
        onFileUpload(event) {
        this.FILE = event.target.files[0];
        this.previewImage = URL.createObjectURL(this.FILE);
        this.submitted = true;
        },
        getMovie(id){
            MovieService.getMovie(id).then(response => {
                this.currentMovie = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        },
        onSubmit() {
        const formData = new FormData()
        formData.append('thumbnail' , this.FILE , this.FILE.name)
        var object = {};
        object['name'] = this.currentMovie.name
        object['description'] = this.currentMovie.description
        object['view'] = this.currentMovie.view
        object['url'] = this.currentMovie.url
        object['duration'] = this.currentMovie.duration


        object['movieType'] = this.currentMovie.movieType.name
        object['casts'] = this.currentMovie.casts
        object['categories'] = this.currentMovie.categories
        object['directors'] = this.currentMovie.directors

        var json = JSON.stringify(object);
        formData.append('jsonObject' , json)
        MovieService.updateMovie(this.currentMovie.id , formData).then(response => {
                console.log(response.data);
                this.message = 'The Movie is updated ';
                this.$router.push({name : "movie-list"});
            }).catch(e => {
                console.log(e);
            });
        },    
         retrieveCategory(){
            axios.get('http://localhost:8080/api/v1/category/admin/list/getAll?isDeleted=false' , { headers: authHeader() } ).then(response => {
                this.optionCategory = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        },
        retrieveCast(){
            axios.get('http://localhost:8080/api/v1/cast/admin/list/getAll?isDeleted=false' , { headers: authHeader() } ).then(response => {
                this.optionCast = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        },
        retrieveDirector(){
            axios.get('http://localhost:8080/api/v1/director/admin/list/getAll?isDeleted=false' , { headers: authHeader() } ).then(response => {
                this.optionDirector = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        },
        retrieveMovieType(){
            axios.get('http://localhost:8080/api/v1/movie/movietype' , { headers: authHeader() } ).then(response => {
                this.optionMovieType = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        }
    },
     mounted() {
         this.message = '';
         this.getMovie(this.$route.params.id);
         this.retrieveCategory();
         this.retrieveCast();
         this.retrieveDirector();
         this.retrieveMovieType();
     }
}
</script>

<style src = "@suadelabs/vue3-multiselect/dist/vue3-multiselect.css">
</style>

<style scoped>
.previewImage {
    height: 200px;
    width: 200px;
}
.size {
    max-width: 100%;
    max-height: 100%;
}
</style>