function solution(n, build_frame) {
    var answer = {};
    for(var info of build_frame){
        if(info[3] === 1){
            console.log(info.slice(0,3),canInstall(answer,info.slice(0,3)) );
            if(canInstall(answer,info.slice(0,3)) && answer[info.slice(0,3)]==undefined){
                //answer.push([...info.slice(0,3)]);
                answer[[...info.slice(0,3)]]=1;
            }
        }else{
            if(canDelete(answer,info.slice(0,3)) && answer[info.slice(0,3)] !=undefined){
                //answer.splice([...info.slice(0,3)],1);
                delete answer[[...info.slice(0,3)]];
            }
        }
    }
    return Object.keys(answer).sort();
}
function canInstall(answer, info){
    if(info[2]===0){
        //기둥은 바닥 위에 있거나
        if(info[1]===0){return true;}
        //보의 한쪽 끝 부분 위에 있거나
        if(answer[[info[0]-1,info[1],1]]!=undefined){
            return true;
        }
        //다른 기둥 위에 있어야 합니다.
        if(answer[[info[0],info[1]-1,0]]!=undefined){
            return true;
        }
    }else{
        //보는 한쪽 끝 부분이 기둥 위에 있거나
        if(answer[[info[0],info[1]-1,0]]!=undefined || answer[[info[0],info[1]+1,0]]!=undefined){
            return true;
        }
        //양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다
        if(answer[[info[0]-1,info[1],1]]!=undefined && answer[[info[0]+1,info[1],1]]!=undefined){
           return true;
        }
    }
    return false;
}
function canDelete(answer,info){
    if(info[2]===0){
        //위에 기둥이 없거나
        //위에 기둥이 있고 양 옆중 보가 있는경우
        //양옆에 보가 없는 경우
        //기둥이 없어도 보가 있을수 있는경우
    }else{
        //끝에 기둥이 없거나
        //끝에 기둥이 있고 아래 기둥이 있는경우
        //끝에 기둥이 있고 연결된 보가 있을수 있는경우
        //연결된 보가 있을수 있는경우
    }
    return false;
}