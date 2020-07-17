import Booking from './Booking.jsx';
import Form from './Form.jsx';
import Header from './Header.jsx';
import Login from './Login.jsx'
import Nav from './Nav.jsx';
import React from 'react';
import request from 'superagent';

export default class App extends React.Component {
    constructor() {
        super();
        this.state = { 
            bookings : [] ,
            showBookings: false,
            token: null
        };
    }

    componentDidMount() {
        if (process.env.NODE_ENV === 'test') return // https://github.com/facebookincubator/create-react-app/issues/3482 - Allegedly will be fixed soon
    }

    loadBookings() {
        request
            .get('/api/booking')
            .set('Authorization', `Bearer ${this.state.token}`)
            .then((res, err) => {
                const bookings = res.body;
                this.setState({
                    bookings,
                    showBookings: true
                });
            }).catch(e => {
                console.log(e);
            })
    }

    loginSuccess(token) {
        this.setState({
            token
        }, () => {
            this.loadBookings()
        })
    }

    render() {
        let bookings = null;
        if(this.state.bookings.length > 0){
            bookings = this.state.bookings.map((name, index) => {
                return <div key={name.id}><Booking onDelete={this.loadBookings.bind(this)} token={this.state.token} id={name.id} /></div>
            })
        } else {
            bookings = <p>No bookings found</p>
        }
        
        if (!this.state.showBookings) {
            return(
                <div id="loginWrapper"><Login onSuccess={this.loginSuccess.bind(this)}/></div>
            );
        } else {
            return (
                <div id="wrap">
                    <Nav />
                    <div className="container">
                        <Header />
                        <div id="bookings">
                            {bookings}
                        </div>
                        <Form onSuccess={this.loadBookings.bind(this)} token={this.state.token} />
                    </div>
                </div>);
        }
    }
}