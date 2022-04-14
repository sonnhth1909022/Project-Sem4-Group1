<template>
  <div class="container">
        <div class=" text-center mt-5 ">
        <h1>Director List</h1>
        </div>
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8 text-left"><h2>Director <b>Details</b></h2></div>
                    <div class="col-sm-4">
                    <div class="search-box">
                        <div class="input-group rounded">
                        <input type="search" class="form-control rounded" placeholder="Search" 
                        aria-label="Search" aria-describedby="search-addon"  
                        v-model="searchTitle" />
                        <span class="input-group-text border-0" id="search-addon" @click="getDirectorByName();">
                        <font-awesome-icon :icon = "['fas' , 'search']" />
                        </span>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered border-3">
                <thead>
                    <tr class = "bg-dark text-white">
                         <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Image</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>
                     <tr v-for="director in directors" :key="director.id">
                        <th scope="col" >{{director.id}}</th>
                        <th scope="col" >{{director.name}}</th>
                        <th scope="col" >{{director.description}}</th>
                        <th scope="col" class = "crop"><a :href="'http://localhost:8080/' + director.avt"><img :src =" 'http://localhost:8080/' + director.avt"/></a></th>
                        <th scope="col"><router-link :to="'/director/' + director.id"><font-awesome-icon :icon = "['fas' , 'user-edit']" /></router-link></th>
                        <th scope="col"><font-awesome-icon :icon = "['fas' , 'trash']"   @click="deleteDirector(director.id)" /></th>
            </tr>
                </tbody>
            </table>
             <div class="mb-3">
           Items per page : 
           <select v-model="pageSize" @change = "handlePageSizeChange($event)">
               <option v-for="size in pageSizes" :key="size" :value="size">
                   {{ size }}
               </option>
           </select>
           </div>
             <VueTailwindPagination 
            :current = "page"
            :total = "count"
            :per-page = "pageSize"
            @page-changed = "onPageClick($event)"
            />
            <br />
            <router-link to="/adddirector">
             <button type="button" class="btn btn-dark float-left">Add Director</button>
             </router-link>
        </div>
    </div>


    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8 text-left"><h2>Director Deleted <b>Details</b></h2></div>
                    <div class="col-sm-4">
                    <div class="search-box">
                        <div class="input-group rounded">
                        <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                        <span class="input-group-text border-0" id="search-addon">
                        <font-awesome-icon :icon = "['fas' , 'search']" />
                        </span>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered border-3">
                <thead>
                    <tr class = "bg-dark text-white">
                         <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Image</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Recover</th>
                    </tr>
                </thead>
                <tbody>
                     <tr v-for="director in directorsDelete" :key="director.id">
                        <th scope="col" >{{director.id}}</th>
                        <th scope="col" >{{director.name}}</th>
                        <th scope="col" >{{director.description}}</th>
                        <th scope="col" class = "crop"><a :href="'http://localhost:8080/' + director.avt"><img :src =" 'http://localhost:8080/' + director.avt"/></a></th>
                        <th scope="col"><router-link :to="'/director/' + director.id"><font-awesome-icon :icon = "['fas' , 'user-edit']" /></router-link></th>
                        <th scope="col"><font-awesome-icon :icon = "['fas' , 'exchange-alt']" @click="updateStateChange(director.id)" /></th>
            </tr>
                </tbody>
            </table>
             <div class="mb-3">
           Items per page : 
           <select v-model="pageSizeDelete" @change = "handlePageSizeDeleteChange($event)">
               <option v-for="size in pageSizesDelete" :key="size" :value="size">
                   {{ size }}
               </option>
           </select>
           </div>
             <VueTailwindPagination 
            :current = "pageDelete"
            :total = "countDelete"
            :per-page = "pageSizeDelete"
            @page-changed = "onPageDeleteClick($event)"
            />
        </div>
    </div>    
</div>   
</template>

<script>
import DirectorService from '../../services/director/director.service'
import "@ocrv/vue-tailwind-pagination/dist/style.css"
import VueTailwindPagination from "@ocrv/vue-tailwind-pagination"
export default {
     name : "DirectorList",
     components : {
      VueTailwindPagination
    },
    data() {
        return {
            directors: [],
            directorsDelete: [],
            deleteCondition : false,

            page : 1,
            count : 0,
            pageSize : 3,
            pageSizes : [3,6,9],

            pageDelete : 1,
            pageSizeDelete: 3,
            pageSizesDelete: [3,6,9],
            countDelete: 0 ,

            searchTitle : ""
        };
    },
    methods : {
      getRequestParams(deleteCondition , page , pageSize)
      {
        let params = {};
        if(!deleteCondition)
        {
          params["isDeleted"] = deleteCondition;
        }
        if(page)
        {
          params["page"] = page - 1;
        }
        if(pageSize)
        {
          params["size"] = pageSize;
        }
        return params;
      },
      getRequestParamsforDelete(deleteCondition , pageDelete , pageSizeDelete)
      {
        let params = {};
        if(!deleteCondition)
        {
          params["isDeleted"] = !deleteCondition;
        }
        if(pageDelete)
        {
          params["page"] = pageDelete - 1;
        }
        if(pageSizeDelete)
        {
          params["size"] = pageSizeDelete;
        }
        return params;
      },
      retrieveDirector()
      {
          const params = this.getRequestParams(
                this.deleteCondition,
                this.page ,
                this.pageSize
            );

            DirectorService.getAllDirectors(params).then(response => {
               const { datas , totalItems } = response.data.data;
               this.directors = datas;
               this.count = totalItems;
            }).catch(e => {
                console.log(e);
            });
      },
      retrieveDirectorDelete()
      {
            const params = this.getRequestParamsforDelete(
              this.deleteCondition,
              this.pageDelete ,
              this.pageSizeDelete
            );

           DirectorService.getAllDirectors(params).then(response => {
               const { datas , totalItems } = response.data.data;
               this.directorsDelete = datas;
               this.countDelete = totalItems;
            }).catch(e => {
                console.log(e);
            });
      },
      getRequestName(searchTitle)
      {
          let params = {};
          if(searchTitle)
          {
              params["name"] = searchTitle;
              return params;
          }
      },
      getDirectorByName()
      {
           const params = this.getRequestName(
                this.searchTitle
            );

            DirectorService.getDirectorByName(params).then(response => {
                const { datas , totalItems } = response.data.data;
               this.directors = datas;
               this.count = totalItems;
            }).catch(e => {
                console.log(e);
            });
      },
      updateStateChange(id)
      {
            var result = confirm('Want to change the director state ?');
            if(result){
           DirectorService.stateChange(id).then(response => {
               console.log(response.data);
               this.$router.push({name : "director-list"});
               this.refreshList();
            }).catch(e => {
                console.log(e);
            });
            }
            else{
                this.$router.push({name : "director-list"});
            }
      },
      deleteDirector(id){
            var result = confirm('Want to delete ?');
            if(result){
            DirectorService.deleteDirector(id).then(response => {
               console.log(response.data);
               this.$router.push({name : "director-list"});
               this.refreshList();
            }).catch(e => {
                console.log(e);
            });
            }
            else{
                this.$router.push({name : "director-list"});
            }
      },
      refreshList(){
            this.retrieveDirector();
            this.retrieveDirectorDelete();
      },
      onPageClick(value)
        {
            this.page = value;
            this.retrieveDirector();  
        },

        handlePageSizeChange(event)
        {
            this.pageSize = event.target.value;
            this.page = 1;
            this.retrieveDirector();
        },
        onPageDeleteClick(value)
        {
            this.pageDelete = value;
            this.retrieveDirectorDelete();  
        },

        handlePageSizeDeleteChange(event)
        {
            this.pageSizeDelete = event.target.value;
            this.pageDelete = 1;
            this.retrieveDirectorDelete();
        }
    },
    mounted()
    {
        this.retrieveDirector();
         this.retrieveDirectorDelete();
    },
}
</script>

<style scoped>
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Roboto', sans-serif;
}
.table-responsive {
    margin: 30px 0;
}
.table-wrapper {
    min-width: 1000px;
    background: #fff;
    padding: 20px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 10px;
    margin: 0 0 10px;
    min-width: 100%;
}
.table-title h2 {
    margin: 8px 0 0;
    font-size: 22px;
}
.search-box {
    position: relative;        
    float: right;
}
.search-box input {
    height: 34px;
    border-radius: 20px;
    border-color: #ddd;
    box-shadow: none;
}
.search-box input:focus {
    border-color: #3FBAE4;
}
.search-box i {
    color: #a0a5b1;
    position: absolute;
    font-size: 19px;
    top: 8px;
    left: 10px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}
table.table td:last-child {
    width: 130px;
}
table.table td a {
    color: #a0a5b1;
    display: inline-block;
    margin: 0 5px;
}
table.table td a.view {
    color: #03A9F4;
}
table.table td a.edit {
    color: #FFC107;
}
table.table td a.delete {
    color: #E34724;
}
table.table td i {
    font-size: 19px;
}    
.crop {
    height: 200px;
    width : 200px;
    overflow:hidden;
    
}
.crop img {
    height : auto;
    width : 100px;
    padding-top:12px;
    margin-left:32px;
}

.border-3 {
    border-width:3px !important;
    border-color: black;
}
</style>