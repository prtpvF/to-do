import React from 'react';

export default function AddUser(){
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow ">
                    <h2 className="tex-center m-4">Register</h2>
                    <div className="mb-3">
                        <label htmlFor="Username" className="form-label">
                            Username
                        </label>
                        <input  type={"text"} className="form-control" placeholder="enter your  username" name="username"/>

                    </div>

                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">
                            Password
                        </label>
                        <input  type={"text"} className="form-control" placeholder="enter your  password" name="password"/>

                    </div>
                    <button type="submit" className="btn btn-outline-primary">Submit</button>
                    <button type="submit" className="btn btn-outline-danger mx-2">Cancel</button>
                </div>
            </div>
        </div>
    )
}