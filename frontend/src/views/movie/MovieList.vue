<template>
  <div class="container">
        <div class=" text-center mt-5 ">
        <h1>Movie List</h1>
        </div>
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8 text-left"><h2>Movie <b>Details</b></h2></div>
                    <div class="col-sm-4">
                    <div class="search-box">
                        <div class="input-group rounded">
                        <input type="search" class="form-control rounded" placeholder="Search" 
                        aria-label="Search" aria-describedby="search-addon"  
                        v-model="searchTitle" />
                        <span class="input-group-text border-0" id="search-addon" @click="getMovieByName();">
                        <font-awesome-icon :icon = "['fas' , 'search']" />
                        </span>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered border-3">
                <thead>
                    <tr class="bg-dark text-white">
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <!-- <th scope="col">Description</th> -->
                        <th scope = "col"> View </th>
                        <th scope = "col"> Duration </th>
                        <!-- <th scope = "col"> Movie Type </th> -->
                        <!-- <th scope = "col"> Url </th> -->
                        <!-- <th scope = "col"> Image </th> -->
                        <th scope = "col"> Image </th>
                        <th scope = "col"> Director </th>
                        <th scope = "col"> Cast </th>
                        <th scope = "col"> Category </th>
                        <th scope = "col"> Edit </th>
                        <th scope = "col"> Delete </th>
                    </tr>
                </thead>
                <tbody>
                     <tr v-for="movie in movies" :key="movie.id">
                        <th scope="col">{{movie.id}}</th>
                        <th scope="col">{{movie.name}}</th>
                        <!-- <th scope="col">{{movie.description}}</th> -->
                        <th scope="col">{{movie.view}}</th>
                        <th scope="col">{{movie.duration}}</th>
                        <!-- <th scope="col">{{movie.movieType.name}}</th> -->
                        <!-- <th scope="col">{{movie.url}}</th> -->
                        <!-- <th scope="col">{{movie.thumbnail}}</th> -->
                        <th scope="col" class = "crop"><img :src ="movie.thumbnail"/></th>
                        <th scope="col">
                    <ul>
                         <li v-for="director in movie.directors" :key="director">{{director.name}}</li>
                    </ul>
                        </th>
                        <th scope="col">
                    <ul>
                        <li v-for="cast in movie.casts" :key="cast">{{cast.name}}</li>
                    </ul>
                        </th>
                        <th scope="col">
                    <ul>
                        <li v-for="category in movie.categories" :key="category">{{category.name}}</li>
                    </ul>
                        </th>
                        <th scope="col"><router-link :to="'/movie/' + movie.id"><font-awesome-icon :icon = "['fas' , 'user-edit']" /></router-link></th>
                        <th scope="col"><font-awesome-icon :icon = "['fas' , 'trash']"   @click="deleteMovie(movie.id)" /></th>
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
            <router-link to="/addmovie">
             <button type="button" class="btn btn-dark float-left">Add Movie</button>
             </router-link>

             <router-link to="/listmovie">
             <button type="button" class="btn btn-dark float-right">Advanced Search Movie</button>
             </router-link>

        </div>
    </div>  


    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8 text-left"><h2>Movie Deleted <b>Details</b></h2></div>
                    <div class="col-sm-4">
                    <!-- <div class="search-box">
                        <div class="input-group rounded">
                        <input type="search" class="form-control rounded" placeholder="Search" 
                        aria-label="Search" aria-describedby="search-addon"  
                        v-model="searchTitle" />
                        <span class="input-group-text border-0" id="search-addon" @click="getMovieByName();">
                        <font-awesome-icon :icon = "['fas' , 'search']" />
                        </span>
                        </div>
                    </div> -->
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered border-3">
                <thead>
                    <tr class="bg-dark text-white">
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <!-- <th scope="col">Description</th> -->
                        <th scope = "col"> View </th>
                        <th scope = "col"> Duration </th>
                        <!-- <th scope = "col"> Movie Type </th> -->
                        <!-- <th scope = "col"> Url </th> -->
                        <!-- <th scope = "col"> Image </th> -->
                        <th scope = "col"> Image </th>
                        <th scope = "col"> Director </th>
                        <th scope = "col"> Cast </th>
                        <th scope = "col"> Category </th>
                        <th scope = "col"> Edit </th>
                        <th scope = "col"> Recover </th>
                    </tr>
                </thead>
                <tbody>
                     <tr v-for="movie in moviesDelete" :key="movie.id">
                        <th scope="col">{{movie.id}}</th>
                        <th scope="col">{{movie.name}}</th>
                        <!-- <th scope="col">{{movie.description}}</th> -->
                        <th scope="col">{{movie.view}}</th>
                        <th scope="col">{{movie.duration}}</th>
                        <!-- <th scope="col">{{movie.movieType.name}}</th> -->
                        <!-- <th scope="col">{{movie.url}}</th> -->
                        <!-- <th scope="col">{{movie.thumbnail}}</th> -->
                        <th scope="col" class = "crop"><img :src ="movie.thumbnail"/></th>
                        <th scope="col">
                    <ul>
                         <li v-for="director in movie.directors" :key="director">{{director.name}}</li>
                    </ul>
                        </th>
                        <th scope="col">
                    <ul>
                        <li v-for="cast in movie.casts" :key="cast">{{cast.name}}</li>
                    </ul>
                        </th>
                        <th scope="col">
                    <ul>
                        <li v-for="category in movie.categories" :key="category">{{category.name}}</li>
                    </ul>
                        </th>
                       <th scope="col"><router-link :to="'/movie/' + movie.id"><font-awesome-icon :icon = "['fas' , 'user-edit']" /></router-link></th>
                        <th scope="col"><font-awesome-icon :icon = "['fas' , 'exchange-alt']" @click="updateStateChange(movie.id)" /></th>
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
import MovieService from '../../services/movie/movie.service'
import "@ocrv/vue-tailwind-pagination/dist/style.css"
import VueTailwindPagination from "@ocrv/vue-tailwind-pagination"
export default {
     name : "MovieList",
      components : {
      VueTailwindPagination
    },
    data() {
        return {
            movies: [],
            moviesDelete : [] ,
            deleteCondition : false,
            searchTitle : "",
            page : 1,
            count : 0,
            pageSize : 3,
            pageSizes : [3,6,9],

            pageDelete : 1,
            pageSizeDelete: 3,
            pageSizesDelete: [3,6,9],
            countDelete: 0
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
      getRequestName(searchTitle)
      {
          let params = {};
          if(searchTitle)
          {
              params["name"] = searchTitle;
              return params;
          }
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
      retrieveMovie()
      {
          const params = this.getRequestParams(
                this.deleteCondition,
                this.page ,
                this.pageSize
            );

            MovieService.getAllMovies(params).then(response => {
               const { datas , totalItems } = response.data.data;
               this.movies = datas;
               this.count = totalItems;
            }).catch(e => {
                console.log(e);
            });
      },
      retrieveMovieDelete()
      {
            const params = this.getRequestParamsforDelete(
              this.deleteCondition,
              this.pageDelete ,
              this.pageSizeDelete
            );

           MovieService.getAllMovies(params).then(response => {
               const { datas , totalItems } = response.data.data;
               this.moviesDelete = datas;
               this.countDelete = totalItems;
            }).catch(e => {
                console.log(e);
            });
      },
      getMovieByName()
      {
           const params = this.getRequestName(
                this.searchTitle
            );

            MovieService.getMovieByName(params).then(response => {
                const { datas , totalItems } = response.data.data;
               this.movies = datas;
               this.count = totalItems;
            }).catch(e => {
                console.log(e);
            });
      },
      updateStateChange(id)
      {
            var result = confirm('Want to change the movie state ?');
            if(result){
           MovieService.stateChange(id).then(response => {
               console.log(response.data);
               this.$router.push({name : "movie-list"});
               this.refreshList();
            }).catch(e => {
                console.log(e);
            });
            }
            else{
                this.$router.push({name : "movie-list"});
            }
      },
      deleteMovie(id){
            var result = confirm('Want to delete ?');
            if(result){
            MovieService.deleteMovie(id).then(response => {
               console.log(response.data);
               this.$router.push({name : "movie-list"});
               this.refreshList();
            }).catch(e => {
                console.log(e);
            });
            }
            else{
                this.$router.push({name : "movie-list"});
            }
      },
      refreshList(){
            this.retrieveMovie();
            this.retrieveMovieDelete();
      },
        onPageClick(value)
        {
            this.page = value;
            this.retrieveMovie();  
        },

        handlePageSizeChange(event)
        {
            this.pageSize = event.target.value;
            this.page = 1;
            this.retrieveMovie();
        },
        onPageDeleteClick(value)
        {
            this.pageDelete = value;
            this.retrieveMovieDelete();  
        },

        handlePageSizeDeleteChange(event)
        {
            this.pageSizeDelete = event.target.value;
            this.pageDelete = 1;
            this.retrieveMovieDelete();
        }
    },
    mounted()
    {
        this.retrieveMovie();
        this.retrieveMovieDelete();
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