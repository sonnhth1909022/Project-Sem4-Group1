import axios from 'axios'
let user = JSON.parse(localStorage.getItem('user'));

class MovieService
{
    getAllMovies(params)
    {
        return axios.get("http://localhost:8080/api/v1/movie/admin/list" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} )
    }
    getMovieByName(params)
    {
        return axios.get("http://localhost:8080/api/v1/movie/admin/getName" , {headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} )
    }
    getMovieByCategory(params)
    {
        return axios.get("http://localhost:8080/api/v1/movie/admin/getCategory" , {headers : {
            Authorization : 'Bearer ' + user.token
        } ,params}  )
    }
    getMovieByCast(params)
    {
        return axios.get("http://localhost:8080/api/v1/movie/admin/getCast" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} )
    }
    getMovieByDirector(params)
    {
        return axios.get("http://localhost:8080/api/v1/movie/admin/getDirector" , {headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} )
    }
    deleteMovie(id)
    {
        return axios.delete(`http://localhost:8080/api/v1/movie/delete/${id}`, 
        {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        }
        );
    }

    stateChange(id)
    {
        return axios.put(`http://localhost:8080/api/v1/movie/statechange/${id}?isDeleted=false` , null , { headers : {
            Authorization : 'Bearer ' + user.token
        } });
    }

    updateMovie(id , data)
    {
        return axios.put(`http://localhost:8080/api/v1/movie/update/${id}` , data , { headers : {
            Authorization : 'Bearer ' + user.token
        } });
    }

    getMovie(id)
    {
        return axios.get(`http://localhost:8080/api/v1/movie/admin/getMovie/${id}` , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }
}

export default new MovieService();