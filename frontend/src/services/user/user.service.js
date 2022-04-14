import axios from 'axios'
let user = JSON.parse(localStorage.getItem('user'));

class UserService
{

    getTransaction(params)
    {
        return axios.get("http://localhost:8080/api/v1/user/admin/user/transaction" , { headers : {
            Authorization : 'Bearer ' + user.token
        } , params } );
    }

    getTransactionByName(params)
    {
        return axios.get("http://localhost:8080/api/v1/user/admin/transaction/getName" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} )
    }

    deleteTransaction(id)
    {
        return axios.delete(`http://localhost:8080/api/v1/user/admin/delete/${id}` , {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        });
    }
    totalSum()
    {
        return axios.get("http://localhost:8080/api/v1/user/admin/user/sum" , {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        });
    }

    userVip()
    {
        return axios.get("http://localhost:8080/api/v1/user/getAll/vip" , {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        });
    }

    userNormal()
    {
        return axios.get("http://localhost:8080/api/v1/user/getAll/normal" , {
            headers : {
                Authorization : 'Bearer ' + user.token
            }
        });
    }

    stateChange(id)
    {
        return axios.put(`http://localhost:8080/api/v1/user/statechange/admin/${id}?isDeleted=false`, null , { headers : {
            Authorization : 'Bearer ' + user.token
        } });
    }

    getTransactionTotal()
    {
        return axios.get("http://localhost:8080/api/v1/user/admin/user/sum" , { headers : {
            Authorization : 'Bearer ' + user.token
        } } )
    }

    getAllUsers()
    {
        return axios.get("http://localhost:8080/api/v1/user/getAll" , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }

    getUserById(id)
    {
        return axios.get(`http://localhost:8080/api/v1/user/user/get/${id}` , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }

    updateUser(id , data)
    {
        return axios.put(`http://localhost:8080/api/v1/user/updateUser/${id}` , data , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }

    updateAccount(id , account)
    {
        return axios.put(`http://localhost:8080/api/v1/user/updateAccount/${id}?accountType=${account}` , null , { headers : {
            Authorization : 'Bearer ' + user.token
        } } );
    }

    getUserByName(params)
    {
        return axios.get("http://localhost:8080/api/v1/user/name" , { headers : {
            Authorization : 'Bearer ' + user.token
        } ,params} );
    }

    

}   

export default new UserService();