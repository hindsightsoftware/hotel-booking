import React from 'react';
import App from '../src/components/App.jsx';
import Booking from '../src/components/Booking.jsx';
import Nav from '../src/components/Nav.jsx';
import Footer from '../src/components/Footer.jsx';
import Header from '../src/components/Header.jsx';
import Form from '../src/components/Form.jsx';
import nock from 'nock';

test('App component', () => {
    const testBookings = [
        {
            "id": 1
        },{
            "id": 2
        }
    ]

    const appWrapper = shallow(
        <App />
    );

    appWrapper.setState({bookings : testBookings});

    expect(appWrapper).toMatchSnapshot();
});

test('Booking component', () => {
    const testBooking = {
        "firstname": "Billy",
        "lastname": "Brown",
        "totalprice": 90,
        "depositpaid": true,
        "additionalneeds": "Jam",
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        }
    }

    const bookingEntry = shallow(
        <Booking id="1" />
    )

    bookingEntry.setState({ booking: testBooking })

    expect(bookingEntry).toMatchSnapshot();
});

test('Nav component', () => {
    const navBar = shallow(
        <Nav />
    )

    expect(navBar).toMatchSnapshot();
});

test('Footer component', () => {
    const footer = shallow(
        <Footer />
    )

    expect(footer).toMatchSnapshot();
});

test('Header component', () => {
    const header = shallow(
        <Header />
    )

    expect(header).toMatchSnapshot();
});

test('Form component', () => {
    const form = shallow(
        <Form />
    )

    expect(form).toMatchSnapshot();
})

test('Submit booking', (done) => {
    
    const expectedPayload = { 
        "firstname" : "Mark",
        "lastname" : "Winteringham",
        "totalprice" : "123",
        "depositpaid" : "true",
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2018-01-02"
        }, "additionalneeds" : "Breakfast"
    }

    nock('http://localhost')
      .post('/booking', expectedPayload)
      .reply(201, () => {
          done();
      })

    const form = mount(
        <Form />
    )

    form.find('#firstname').instance().value = "Mark"
    form.find('#lastname').instance().value = "Winteringham"
    form.find('#totalprice').instance().value = "123"
    form.find('#depositpaid').instance().value = "true"
    form.find('#additionalneeds').instance().value = "Breakfast"
    form.find('#checkin').instance().value = "2018-01-01"
    form.find('#checkout').instance().value = "2018-01-02"
    form.find('button').simulate('click');
})