import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from "react-router-dom";
import './granter_updates.css';
import redRose from '../../../../assets/img/red-rose.png';
import dollarSign from '../../../../assets/svg/dollar-sign.svg';
import direction from '../../../../assets/svg/direction.svg';
import calendar from '../../../../assets/svg/calendar.svg';
import SockJS from "sockjs-client";
import Stomp from "stompjs";

function GranterUpdates () {
    const [stompClient, setStompClient] = useState(null);
    const [jobOffers, setJobOffers] = useState([]);
    const [connected, setConnected] = useState(false);
    const token = localStorage.getItem('jwtToken');
    const providerEmail = localStorage.getItem('username');
    const navigate = useNavigate();

    useEffect(() => {
        console.log('Component mounted'); // Log when component mounts

        if (!token || !providerEmail) {
            console.log("Token or provider email not found!");
            return;
        }

        const socket = new SockJS('http://localhost:8080/ws');
        const client = Stomp.over(socket);

        client.connect({'Authorization': `Bearer ${token}`}, function(frame) {
            console.log('Connected: ' + frame);
            setStompClient(client);
            setConnected(true);

            const subscriptionUrl = `/user/${providerEmail}/queue/job-offers`;
            client.subscribe(subscriptionUrl, function(message) {
                console.log('Job offer received:', message.body); // Log when a message is received
                setJobOffers([JSON.parse(message.body)]);
            });

            console.log('Subscribed to:', subscriptionUrl); // Log the subscription
        }, function(error) {
            console.log('Connection error: ' + error);
            setConnected(false);
        });

        return () => {
            console.log('Component will unmount'); // Log when component unmounts
            if (client && client.connected) {
                client.disconnect(() => console.log('Disconnected'));
            }
        };
    }, []);

    const respondToJobOffer = (jobId, providerId, status) => {
        if (stompClient && stompClient.connected) {
            stompClient.send("/app/respondToJob", {}, JSON.stringify({ jobId, providerId, status }));
            setJobOffers(prevOffers => prevOffers.filter(offer => offer.jobId !== jobId));
            navigate('/granter/details');
        } else {
            console.log('Not connected');
        }
    };

    return (
        <div className='granterUpdates'>
            <div className='granterUpdatesTitle'>
                <span className='granterUpdatesTitleView'>View </span>
                <span className='granterUpdatesTitleWishes'>Wishes </span>
                <span className='granterUpdatesTitleText'>Near You</span>
            </div>

            {jobOffers.map((offer, index) => (
                <div className='wishBoxContainer'>
                    <div className='wishBoxContainerLeft'>
                        <div className='viewImageContainer'>
                            <img className="viewImage" src={redRose} alt={"Red-Rose.png"} />
                        </div>

                        <div className='generalWishInformation'>
                            <span className='generalWishInformationTitle'>{offer.description}</span>

                            <div className='guaranteedAmountContainer'>
                                <img className='dollarSign' src={dollarSign} alt='Dollar Sign Icon' />
                                <span className='guaranteedAmount'>${offer.itemPrice} Guaranteed</span>
                            </div>

                            <div className='estimatedMilesContainer'>
                                <img className='direction' src={direction} alt='Direction Icon' />
                                <span className='estimatedMiles'>2.7 mi Estimated</span>
                            </div>

                            <div className='estimatedTimeToBeDeliveredContainer'>
                                <img className='calendar' src={calendar} alt='Calendar Icon' />
                                <span className='estimatedTimeToBeDelivered'>Deliver by 5:00 PM on Dec 25</span>
                            </div>
                        </div>
                    </div>

                    <div className='wishBoxContainerRight'>
                        <div className='wishBoxContainerRightUp'>
                            <button className="acceptButton" onClick={() => respondToJobOffer(offer.jobId, providerEmail, 'ACCEPTED')}>Accept</button>
                        </div>
                        <div className='wishBoxContainerRightDown'>
                            <button className="declineButton" onClick={() => respondToJobOffer(offer.jobId, providerEmail, 'DECLINED')}>Decline</button>
                        </div>
                    </div>
            </div>
            ))}
        </div>
    )

}

export default GranterUpdates;