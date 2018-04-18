import React from 'react';
import request from 'superagent';
import Booking from './Booking.jsx';
import Nav from './Nav.jsx';
import Header from './Header.jsx';
import Form from './Form.jsx';

export default class App extends React.Component {
    constructor() {
        super();
        this.state = { bookings : [] };
    }

  componentDidMount() {
    if (process.env.NODE_ENV === 'test') return // https://github.com/facebookincubator/create-react-app/issues/3482 - Allegedly will be fixed soon

    request
        .get('/booking')
        .then((res, err) => {
            const bookings = res.body;
            this.setState({bookings});
        }).catch(e => {
            console.log(e);
        })
  }

  render() {
    let bookings = null;
    if(this.state.bookings.length > 0){
        bookings = this.state.bookings.map((name, index) => {
            return <div key={name.id}><Booking id={name.id} /></div>
        })
    } else {
        bookings = <p>No bookings found</p>
    }

    return (
        <div id="wrap">
            <Nav />
            <div className="container">
                <Header />
                <div id="bookings">
                    {bookings}
                </div>
                <Form />
            </div>
        </div>);
  }
}