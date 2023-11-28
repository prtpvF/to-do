import React, {useEffect,useState} from 'react';
import axios from 'axios';

export default function Home(){
    const [users,setUsers]=useState([])
    useEffect(() => {
        loadUsers();
    },[]);

    const loadUsers=async()=>{
        const result=await axios.get("http://localhost:8080/person/all");
        setUsers(result.data);
    }

    
    return(
        <div className="container">
        <div className='py-4'>
            <table className="table border shadow">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Password</th>
                    <th scope="col">Id</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                {
                    users.map((user,index)=>(
                    <tr>
                    <th scope="row" key={index}>{index+1}</th>
                    <td>{user.username}</td>
                    <td>{user.password}</td>
                    <td>{user.id}</td>
                    </tr>
                    ))
                }


                </tbody>
            </table>
        </div>
        </div>
    )
}