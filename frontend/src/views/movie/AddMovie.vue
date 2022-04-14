<template>
  <div class="container"> 
      <div class=" text-center mt-5 ">
        <h3>Add a Movie</h3>
      </div>
    <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <div v-if = "!submitted">
                    <form @submit.prevent="onSubmit">
                            <div class="form-group">
                            <label for="title" class="float-left">Name</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="name"
                                name = "title"
                            />
                            </div>
                            <div class="form-group">
                            <label for="description" class="float-left">Description</label>
                            <textarea  type="text"
                                class="form-control"
                                id="description"
                                required
                                v-model="description"
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
                                v-model="url"
                                name = "url"
                            />
                            </div>

                            <div class="form-group">
                            <label for="duration" class="float-left">Duration</label>
                            <input type="number"
                                class="form-control"
                                id="duration"
                                required
                                v-model="duration"
                                name = "duration"
                            />
                            </div>

                            <div class="form-group">
            <multiselect
              v-model="categories"
              :options="optionCategory"
              :multiple="true"
              :close-on-select="true"
              placeholder="Categories"
              label="name"
              track-by="id"
            ></multiselect>
          
                            </div>

        <div class="form-group">
            <multiselect
              v-model="casts"
              :options="optionCast"
              :multiple="true"
              :close-on-select="true"
              placeholder="Casts"
              label="name"
              track-by="id"
            ></multiselect>
        </div>

        <div class="form-group">
            <multiselect
              v-model="directors"
              :options="optionDirector"
              :multiple="true"
              :close-on-select="true"
              placeholder="Directors"
              label="name"
              track-by="id"
            ></multiselect>
        </div>

                            <div class="form-group">                       
                            <input type = "file" @change="onFileUpload" class="float-left">
                            <div v-if="previewImage" class="previewImage">
                            <img class="preview my-3 float-left size" :src="previewImage" alt="" />  
                            </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                            <button class="btn btn-primary btn-block btn-lg">Add Movie</button>
                            </div>
                    </form>
                        </div>
                        <div v-else>
                         <h4>{{message}}</h4>
                        <button class="btn btn-success" @click="newMovie">Add Movie</button>
                        </div>
                </div>
            </div> 
        </div> 
    </div>
</div>
</div>
</template>

<script>
import axios from "axios";
import authHeader from '../../services/auth-header';
import Multiselect from '@suadelabs/vue3-multiselect';
export default {
    name : "add-movie",
    components: { Multiselect },
    data() {
    return {
      FILE: null,
      name : '',
      description: '',
      view : null ,
      duration : null,
      url : '',
      casts: [],
      categories: [],
      directors: [],
      optionCategory: [],
      optionDirector: [],
      optionCast: [],
      submitted : false,
      previewImage: undefined,
      message : ''
    };
    },
     methods: {
        onFileUpload(event) {
        this.FILE = event.target.files[0];
        this.previewImage = URL.createObjectURL(this.FILE);
        },

        onSubmit() {
        const formData = new FormData()
        formData.append('thumbnail' , this.FILE , this.FILE.name)
        var object = {};
        object['name'] = this.name
        object['description'] = this.description
        object['view'] = this.view
        object['url'] = this.url
        object['movieType'] = null
        object['duration'] = this.duration
        object['casts'] = this.casts
        object['categories'] = this.categories
        object['directors'] = this.directors
        var json = JSON.stringify(object);
        formData.append('jsonObject' , json)
        axios.post('http://localhost:8080/api/v1/movie/add' , formData , { headers: authHeader() } , {
        }).then((res) => {
        console.log(res);
        this.submitted = true;
        this.message = res.data.message;
        })
        },
        newMovie(){
            this.submitted = false;
            this.FILE = null;
            this.name = '';
            this.description = '',
            this.view = '',
            this.url = '',
            this.duration = '',
            this.casts = [],
            this.categories = [],
            this.directors = []
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
    },
     mounted() {
      this.retrieveCategory();
      this.retrieveCast();
      this.retrieveDirector();
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