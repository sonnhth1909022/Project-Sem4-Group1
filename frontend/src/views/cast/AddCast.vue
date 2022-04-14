<template>
<br />
<br />
<div class="container"> 
      <div class=" text-center mt-5 ">
        <h3>Add a Cast</h3>
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
                            <input type="text"
                                class="form-control"
                                id="description"
                                required
                                v-model="description"
                                name = "description"
                            />
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
                            <button class="btn btn-primary btn-block btn-lg">Add Cast</button>
                            </div>
                    </form>
                        </div>
                        <div v-else>
                         <h4>{{message}}</h4>
                        <button class="btn btn-success" @click="newCast">Add Cast</button>
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
export default {
    name : "Add-Director",
    data(){
            return {
                FILE: null,
                name : '',
                description: '',
                submitted : false,
                previewImage: undefined,
                message: ""
            };
    },
    methods: {
        onFileUpload(event) {
        this.FILE = event.target.files[0];
        this.previewImage = URL.createObjectURL(this.FILE);
        },

        onSubmit() {
        let user = JSON.parse(localStorage.getItem('user'));
        const formData = new FormData()
        formData.append('avatar' , this.FILE , this.FILE.name)
        var object = {};
        object['name'] = this.name
        object['description'] = this.description
        var json = JSON.stringify(object);
        formData.append('jsonObject' , json)
        axios.post('http://localhost:8080/api/v1/cast/add' , formData , { headers : {
            Authorization : 'Bearer ' + user.token
        } } , {
        }).then((res) => {
        console.log(res);
        this.submitted = true;
        this.message = res.data.message;
        })
        },
        newCast(){
            this.submitted = false;
            this.FILE = null;
            this.name = '';
            this.description = '',
            this.previewImage = null
        }
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