<template>
<br />
<br />
<div class="container"> 
      <div class=" text-center mt-5 ">
        <h3>Update a Cast</h3>
      </div>
    <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <div v-if = "currentCast">
                    <form @submit.prevent="onSubmit">
                            <div class="form-group">
                            <label for="title" class="float-left">Name</label>
                            <input type="text" 
                                    class="form-control" 
                                    id="title"
                                    required
                                    v-model="currentCast.name"
                            />
                            </div>
                            <div class="form-group">
                            <label for="description" class="float-left">Description</label>
                            <textarea 
                                    type="text"
                                    class="form-control"
                                    id="description"
                                    required
                                    v-model="currentCast.description"
                                    rows="4" cols="50"
                            />
                            </div>
                            <div class="form-group">                       
                            <label for="image" class="float-left">Image</label>
                            
                            <div v-if = "!submitted" class= "previewImage">
                            <img :src =" 'http://localhost:8080/' + currentCast.avt" class="preview my-3 float-left size"/>
                            </div>
                            </div>

                            <div class = "form-group">
                            <div v-if="previewImage" class="previewImage">
                            <img class="preview my-3 float-left size" :src="previewImage" alt="" />  
                            
                            </div>
                            </div>

                            <div class = "form-group">
                            <input type = "file" @change="onFileUpload" class="float-left">
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                            <button class="btn btn-primary btn-block btn-lg">Update Cast</button>
                            </div>

                            <div class="form-group">
                                    <h3>{{message}}</h3>
                            </div>
                    </form>
                        </div>
                        <div v-else>
                         <h4>Please choose a cast</h4>
                        </div>
                </div>
            </div> 
        </div> 
    </div>
</div>
</div>
</template>

<script>
import CastService from '../../services/cast/cast.service'
export default {
    name: "cast",
    data() {
        return {
            FILE: null,
            currentCast : null,
            message : '',
            previewImage: undefined,
            submitted : false
        };
    },
    methods: {
        onFileUpload(event) {
        this.FILE = event.target.files[0];
        this.previewImage = URL.createObjectURL(this.FILE);
        this.submitted = true
        },
        getCast(id){
            CastService.getCast(id).then(response => {
                this.currentCast = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        },
        onSubmit() {
        const formData = new FormData()
        formData.append('avatar' , this.FILE , this.FILE.name)
        var object = {};
        object['name'] = this.currentCast.name
        object['description'] = this.currentCast.description
        var json = JSON.stringify(object);
        formData.append('jsonObject' , json)
        CastService.updateCast(this.currentCast.id , formData).then(response => {
                console.log(response.data);
                this.message = 'The Cast is updated ';
                this.$router.push({name : "cast-list"});
            }).catch(e => {
                console.log(e);
            });
        }
    },
    mounted(){
        this.message = '';
        this.getCast(this.$route.params.id);
    }
}
</script>

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