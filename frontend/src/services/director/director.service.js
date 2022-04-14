import axios from 'axios'
let user = JSON.parse(localStorage.getItem('user'));
// const API_URL = 'http://localhost:8080/api/v1/category/';

class DirectorService
{
    getAllDirectors(params)
    {
        return axios.get("http://localhost:8080/api/v1/director/admin/list" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} );
    }
    getDirectorByName(params)
    {
        return axios.get("http://localhost:8080/api/v1/director/admin/list/getName" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} )
    }
    deleteDirector(id)
    {
        return axios.delete(`http://localhost:8080/api/v1/director/delete/${id}` , {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        });
    }

    stateChange(id)
    {
        return axios.put(`http://localhost:8080/api/v1/director/statechange/${id}?isDeleted=false`, null , { headers : {
            Authorization : 'Bearer ' + user.token
        } });
    }

    updateDirector(id , data)
    {
        return axios.put(`http://localhost:8080/api/v1/director/update/${id}` , data , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }

    getDirector(id)
    {
        return axios.get(`http://localhost:8080/api/v1/all/director/get/${id}` , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }
}

export default new DirectorService();