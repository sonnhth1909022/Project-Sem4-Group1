import axios from 'axios'
let user = JSON.parse(localStorage.getItem('user'));
// const API_URL = 'http://localhost:8080/api/v1/category/';

class CastService
{
    getAllCasts(params)
    {
        return axios.get("http://localhost:8080/api/v1/cast/admin/list" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params}  )
    }
    getCastByName(params)
    {
        return axios.get("http://localhost:8080/api/v1/cast/admin/cast/getName" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params}  )
    }
    deleteCast(id)
    {
        return axios.delete(`http://localhost:8080/api/v1/cast/delete/${id}` , {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        } );
    }

    stateChange(id)
    {
        return axios.put(`http://localhost:8080/api/v1/cast/statechange/${id}?isDeleted=false` , null , { headers : {
            Authorization : 'Bearer ' + user.token
        } });
    }

    updateCast(id , data)
    {
        return axios.put(`http://localhost:8080/api/v1/cast/update/${id}` , data , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }

    getCast(id)
    {
        return axios.get(`http://localhost:8080/api/v1/all/cast/get/${id}` , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }
}

export default new CastService();