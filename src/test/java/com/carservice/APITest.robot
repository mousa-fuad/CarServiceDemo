*** Settings ***
Test Timeout        1 minute
Library             RequestsLibrary
Library             Collections
Library             JsonValidator
Library             Process
Library             OperatingSystem
Suite Setup         Ping Server

*** Variables ***
#ENDPOINTS              ---
${BASE_URL}             https://localhost:4000
${cars}                 /cars
#LOOP COUNTER           ---
${COUNTER}              ${1}
#HEADERS                ---
${CONTENT_TYPE}         application/json
#AUTH                   ---
#CAR DETAILS        ---
${id}            0
${model}             bmw
${price}           1

*** Test Cases***

Add New Car
    [Tags]  Post
    Add New Car

Update New Car
    [Tags]  Put
    Update New Car

Partial Update New Car
    [Tags]  Patch
    Partial Update New Car


***Keywords***
Ping Server
    Create Session      ping        ${BASE_URL}     verify=True
    ${response}=        Get Request     ping        uri=/ping
    Should Be Equal As Strings      ${response.status_code}     201

Add Car
    ${newcar}=      Create Dictionary
    ...                 id=${id}
    ...                 model=${model}
    ...                 price=${price}
    ${HEADERS}=          Create Dictionary
    ...                  Content-Type=${CONTENT_TYPE}
    ...                  User-Agent=RobotFramework
    Create Session      Add Car     ${BASE_URL}     verify=True
    ${response}=        Post Request    Add Car     uri=${cars}   data=${newcar}  headers=${HEADERS}
    Should Be Equal As Strings      ${response.status_code}     200
    Element should exist     ${response.content}         .carid
    ${newid}=       Select Elements     ${response.content}     .carid
    Set Suite Variable      ${NEW_ID}       ${newid}[0]


Check New Car Details Are Correct
    Create Session      Get New     ${BASE_URL}     verify=True
    ${response}=        Get Request     Get New     uri=${cars}/${NEW_ID}
    Should Be Equal As Strings      ${response.status_code}     200
    Element Should Exist        ${response.content}     .id:contains("${id}")
    Element Should Exist  ${response.content}  .model:contains("${model}")
    Element Should Exist  ${response.content}  .price:(${price})
