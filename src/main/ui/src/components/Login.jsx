import React from 'react';
import request from 'superagent';

export default class Login extends React.Component {
    constructor() {
        super();
        this.state = { 
            username: '',
            password: '',
            token: ''
        };
    }

handleLogin() {
    const { username, password } = this.state
    request
            .post('/login')
            .send({
                username,
                password
            })
            .then((res) => {
                const body = JSON.parse(res.text);
                this.props.onSuccess(body.token);
            }).catch(e => {
                console.error(e);
            })
}

handleUsernameChange(e){
  console.log(e.target.value)
  this.setState({
      username: e.target.value
  })
}

handlePasswordChange(e) {
   this.setState({
       password: e.target.value
   }) 
}

render() {
    return (
        <div className="row form">
            <div className="col-md-1"><input type="text" id="username" onChange={(e) => {this.handleUsernameChange(e)}} className="form-control input-sm" placeholder="admin" /></div>
            <div className="col-md-2"><input type="text" id="email" onChange={(e) => {this.handlePasswordChange(e)}} className="form-control input-sm" placeholder="password123" /></div>
            <div className="col-md-1"><button onClick={this.handleLogin.bind(this)} className="btn btn-sm">Login</button></div>
            <p>Use <strong>admin</strong> as a username and <strong>password123</strong> for password</p>
        </div>)
    }
}