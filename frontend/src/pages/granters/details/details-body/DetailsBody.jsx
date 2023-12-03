import React, {useEffect, useState} from 'react';
import './DetailsBody.css';
import Hourglass_icon from "../../../../assets/img/Hourglass.png"
import {Link} from "react-router-dom";
import Red_Rose from "../../../../assets/img/red-rose.png"
import axios from "axios";

function DetailsBody () {

    const [assignedJob, setAssignedJob] = useState(null);
    const token = localStorage.getItem('jwtToken');
    const providerEmail = localStorage.getItem('username');

    const completeJob = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/providers/${providerEmail}/complete`, {}, {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            console.log('Job completion response:', response);
        } catch (error) {
            console.error('Error completing job:', error);
        }
    };

    useEffect(() => {
        const fetchAssignedJob = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/providers/${providerEmail}/my-assigned-job`, {
                    headers: { 'Authorization': `Bearer ${token}` }
                });
                setAssignedJob(response.data);
            } catch (error) {
                console.error('Error fetching assigned job:', error);
            }
        };

        if (providerEmail) {
            fetchAssignedJob();
        }
    }, [providerEmail, token]);


    return (
        <div className='detailsBody'>
            <div className={'detailsTitle'}>
                <span className={'detailsTitleTextOne'}>Wish&nbsp;</span>
                <span className={'detailsTitleTextTwo'}>Details</span>
            </div>
            {assignedJob &&
                <div className={'detailsContent'}>
                    <div className={'detailsContentBox'}>
                        <span className={'detailsContentTitle'}>Wish:&nbsp; </span>
                        <span className={'detailsContentTitle'}>{assignedJob.description}</span>
                    </div>
                    <div className={'detailsContentBox'}>
                        <span className={'detailsContentTitle'}>Product Information:</span>
                    </div>
                    <div className={'detailsContentBox'}>
                        <img className={'Red_Rose'} src={Red_Rose} alt={"Red_Rose"}/>
                        <div className={'detailsSub'}>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Name: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.description}</span>
                            </div>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Quantity: </span>
                                <span className={'detailsSubDesc'}>1</span>
                            </div>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Item price: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.itemPrice}</span>
                            </div>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Arrival Date: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.endDate}</span>
                            </div>
                        </div>
                    </div>
                    <div className={'detailsContentBox'}>
                        <span className={'detailsContentTitle'}>Delivery Instructions/Notes:</span>
                    </div>
                    <div className={'detailsContentBox'}>
                        <span className={'detailsSubDesc'}>{assignedJob.description}</span>
                    </div>
                    <div className={'detailsContentBox'}>
                        <span className={'detailsContentTitle'}>Recipient’s Information:</span>
                    </div>
                    <div className={'detailsContentBox'}>
                        <div className={'detailsSub'}>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Recipient's Name: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.receiverName}</span>
                            </div>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Recipient's Phone Number: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.receiverPhone}</span>
                            </div>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Recipient's Email: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.receiverEmail}</span>
                            </div>
                            <div className={'detailsSubBox'}>
                                <span className={'detailsSubTitle'}>Recipient's Shipping Address: </span>
                                <span className={'detailsSubDesc'}>{assignedJob.receiverAddress}</span>
                            </div>
                        </div>
                    </div>
                </div>
            }
            <Link to={"/granter/wish-granted"} className={"completeToGranted"} onClick={completeJob} >Complete</Link>
        </div>
    )
}

export default DetailsBody;