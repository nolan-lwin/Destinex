import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import 'react-phone-number-input/style.css';
import PhoneInput from 'react-phone-number-input';
import { CountryDropdown, RegionDropdown } from 'react-country-region-selector';
import './wish_form_recipient.css';
import axios from "axios";

function WishFormRecipient() {
    // Might not need these until last page?
    const [receiverFirstName, setReceiverFirstName] = useState('');
    const [receiverLastName, setReceiverLastName] = useState('');
    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState('');
    const [firstAddress, setFirstAddress] = useState('');
    const [secondAddress, setSecondAddress] = useState('');
    const [country, setCountry] = useState('');
    const [region, setRegion] = useState('');
    const [city, setCity] = useState('');
    const [zip, setZip] = useState('');
    const [address, setAddress] = useState('');
    const [addressPoint, setAddressPoint] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate();

    const validateForm = () => {
        if (!receiverFirstName || !receiverLastName || !phone || !email || !firstAddress || !country || !region || !city || !zip) {
            setErrorMessage('Please fill in all required information!');
            return false;
        }
        return true;
    };

    const handleNextClick = () => {
        const isValid = validateForm();
        if (isValid) {
            navigate('/wish-additional');
        }
    };

    const handleFirstNameChange = (event) => {
        // Might not need this until last page if using sessionStorage
        const newFirstName = event.target.value
        setReceiverFirstName(newFirstName)
        sessionStorage.setItem("receiverFirstName", newFirstName)
    }

    const handleLastNameChange = (event) => {
        // Might not need this until last page if using sessionStorage
        const newLastName = event.target.value
        setReceiverLastName(newLastName)
        sessionStorage.setItem("receiverLastName", newLastName)
    }

    const handleContactChange = (phoneNumber) => {
        // Might not need this until last page if using sessionStorage
        const newPhone = phoneNumber
        setPhone(newPhone)
        sessionStorage.setItem("receiverPhone", newPhone)
    }

    const handleEmailChange = (event) => {
        // Might not need this until last page if using sessionStorage
        const newEmail = event.target.value
        setEmail(newEmail)
        sessionStorage.setItem("receiverEmail", newEmail)
    }

    const getCoordinates = async (address) => {
        const url = `https://nominatim.openstreetmap.org/search.php?q=${address}&format=jsonv2`;

        try {
            const response = await axios.get(url);
            console.log('response',response.data)
            if (response.data[0]) {
                const lat = response.data[0].lat;
                const lon = response.data[0].lon;
                return { lat, lon };
            } else {
                return null;
            }
        } catch (error) {
            console.error('Geocoding error:', error);
            return null;
        }
    };

    const processAddress = async () => {
        const coords = await getCoordinates(`${firstAddress}, ${city}`);
        if (coords) {
            const geoJsonPoint = {
                type: "Point",
                coordinates: [coords.lon, coords.lat],
            };
            setAddressPoint(geoJsonPoint);
            sessionStorage.setItem("receiverAddressPoint", JSON.stringify(geoJsonPoint.coordinates));
        }
        const addressObject = {
            FirstAddress: firstAddress,
            SecondAddress: secondAddress,
            City: city,
            Region: region,
            Country: country,
            Zip: zip,
        };
        sessionStorage.setItem("receiverAddress", JSON.stringify(addressObject));
        sessionStorage.setItem("receiverAddressString", `${firstAddress}, ${secondAddress}, ${city}, ${region}, ${country}`);
    };

    const handleSubmit = async () => {
        const isValid = validateForm();
        if (isValid) {
            await processAddress();
            navigate('/wish-additional');
        }
    };

    return (
        <div className="wishFormForRecipient">
            <div className="wishFormForRecipientTitle">
                <span className="wishFormForRecipientTitleText">Make a </span>
                <span className="wishFormForRecipientTitleWish">Wish</span>
                <p>{errorMessage && <span>{errorMessage}</span>}</p>
            </div>

            <div className='wishFormForRecipientContainer'>
                <div className='step2'>
                    <span className='step2Text'>Step 2 of 4</span>
                </div>

                <div className='wishFormForRecipientSubTitle'>
                    <span className='wishFormForRecipientSubTitleText'>Please provide the details of the person who will be receiving your shipment:</span>
                </div>

                <div className='recipientNameContainer'>
                    <div className='firstName'>
                        <label className='firstNameText'>Recipient's First Name</label>
                        <input className='firstNameInput' type='text'
                           onChange={handleFirstNameChange}
                        />
                    </div>

                    <div className='lastName'>
                        <label className='lastNameText'>Recipient's Last Name</label>
                        <input className='lastNameInput' type='text'
                            onChange={handleLastNameChange}
                        />
                    </div>
                </div>

                <div className='contactAndEmailContainer'>
                    <div className='contactNumber'>
                        <label className='contactNumberText'>Recipient's Phone Number</label>
                        <div className="contactNumberInputContainer">
                            <PhoneInput
                                value={phone}
                                onChange={handleContactChange}
                                international
                                defaultCountry="US"
                            />
                        </div>
                    </div>

                    <div className='emailAddress'>
                        <label className='emailAddressText'>Recipient's Email Address</label>
                        <input className='emailAddressInput' type='email'
                            onChange={handleEmailChange}
                        />
                    </div>
                </div>

                <div className='firstAddress'>
                    <label className='firstAddressText'>Recipient's Address</label>
                    <input className='firstAddressInput' type='text'
                        onChange={(val) => setFirstAddress(val.target.value)}
                    />
                </div>

                <div className='secondAddress'>
                    <label className='secondAddressText'>Apartment, suite, etc.</label>
                    <input className='secondAddressInput' type='text'
                       onChange={(val) => setSecondAddress(val.target.value)}
                    />
                </div>


                <div className='recipientCountry'>
                    <label className='recipientCountryText'>Country</label>
                    <CountryDropdown
                        value={country}
                        onChange={(val) => setCountry(val)}
                        id="country"
                        classes="dropdownClass" // apply your custom styles here
                    />
                </div>

                <div className='recipientState'>
                    <label className='recipientStateText'>State</label>
                    <RegionDropdown
                        country={country}
                        value={region}
                        onChange={(val) => setRegion(val)}
                        id="state"
                        classes="dropdownClass" // apply your custom styles here
                        disableWhenEmpty={true}
                    />
                </div>

                <div className='cityAndZipCode'>
                    <div className='recipientCity'>
                        <label className='recipientCityText'>City</label>
                        <input className='recipientCityInput' type='text'
                           onChange={(val) => setCity(val.target.value)}
                        />
                    </div>

                    <div className='recipientZipCode'>
                        <label className='recipientZipCodeText'>ZIP/Postal Code</label>
                        <input className='recipientZipCodeInput' type='text'
                               onChange={(val) => setZip(val.target.value)}
                        />
                    </div>
                </div>

                <div className='backAndNextButtons'>
                    <Link to='/wish-product' className='backButton'>
                        Back
                    </Link>
                    <button className="nextButton" onClick={handleSubmit}>
                        Next
                    </button>
                </div>
            </div>
        </div>
    )
}

export default WishFormRecipient;