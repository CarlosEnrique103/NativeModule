import React from 'react';
import {Button, View, PermissionsAndroid, Platform} from 'react-native';
import {GetPhoneNumber} from './utilities/UsePhoneNumber';

const App = () => {
  const onPress = async () => {
    // PermissionsAndroid.PERMISSIONS.READ_PHONE_NUMBERS,
    // const userPermissions = await PermissionsAndroid.requestMultiple([
    //   // PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
    //   PermissionsAndroid.PERMISSIONS.READ_PHONE_NUMBERS,
    //   PermissionsAndroid.PERMISSIONS.READ_SMS,
    // ]);

    const userPermissions = await PermissionsAndroid.requestMultiple([
      PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
      PermissionsAndroid.PERMISSIONS.READ_CALL_LOG,
      PermissionsAndroid.PERMISSIONS.READ_CONTACTS,
    ]);

    console.log({ver: Platform.constants['Release']});
    console.log({userPermissions});

    if (
      userPermissions['android.permission.READ_PHONE_STATE'] ===
        PermissionsAndroid.RESULTS.GRANTED &&
      userPermissions['android.permission.READ_CONTACTS'] ===
        PermissionsAndroid.RESULTS.GRANTED &&
      userPermissions['android.permission.READ_CALL_LOG'] ===
        PermissionsAndroid.RESULTS.GRANTED
    ) {
      GetPhoneNumber();
    }
  };

  return (
    <View>
      <Button
        title="Get Phone number"
        color="#841584"
        onPress={async () => await onPress()}
      />
    </View>
  );
};

export default App;
