import React from 'react';
import {Dimmer, Loader} from 'semantic-ui-react';

const Spinner: React.FC = () => (
    <Dimmer active inverted>
        <Loader size="massive" inverted />
    </Dimmer>
);

export default Spinner;
