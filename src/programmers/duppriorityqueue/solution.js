function solution(operations) {
    var answer = [];
    var arr=[];
    for(var tmp of operations){
        var oper=tmp.split(" ");
        if(oper[0]=='I'){
            arr.push(parseInt(oper[1]));
            arr.sort((a,b)=>{return a-b;});
        }else if(oper[1]==1){
            arr.splice(arr.length-1,1);
        }else{
            arr.splice(0,1);
        }
        //console.log(tmp,'>>',arr);
    }
    
    if(arr.length==0){
        answer=[0,0];
    }else if(arr.length==1){
        answer=[arr[0],arr[0]];
    }else{
        answer=[arr[arr.length-1],arr[0]];
    }
    return answer;
}