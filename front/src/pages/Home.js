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

        </div>
        </div>
    )
}