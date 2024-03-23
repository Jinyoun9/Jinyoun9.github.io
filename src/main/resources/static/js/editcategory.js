var categoryAddForm = document.querySelector('.categoryAddForm');
var categoryModifyForm = document.querySelector('.categoryModifyForm');
var categoryDeleteForm = document.querySelector('.categoryDeleteForm');
var otherButtons = document.querySelectorAll('button:not(#add):not(#modify):not(#delete)');
otherButtons.forEach(function(button) {
    button.addEventListener('click', function(event) {
        event.preventDefault(); // 버튼의 기본 동작을 막습니다.
        // 추가적으로 필요한 동작을 여기에 추가할 수 있습니다.
    });
});
document.getElementById('add').addEventListener('click', function(){
    categoryAddForm.style.display='block';
    categoryModifyForm.style.display='none';
    categoryDeleteForm.style.display='none';
});
document.getElementById('modify').addEventListener('click', function(){
    categoryModifyForm.style.display='block';
    categoryAddForm.style.display='none';
    categoryDeleteForm.style.display='none';
});
document.getElementById('delete').addEventListener('click', function(){
    categoryDeleteForm.style.display='block';
    categoryAddForm.style.display='none';
    categoryModifyForm.style.display='none';
});
document.getElementById('deleteSubmit').addEventListener('click', function(){
    const isSelected = document.getElementById("categoryDelete").style.visibility;
    if(isSelected === 'hidden'){
        alert("카테고리가 선택되지 않았습니다. 카테고리를 먼저 선택해주세요.");
    }
    else{
        const answer = confirm('카테고리를 삭제하면 해당 카테고리에 속한 글들도 모두 삭제됩니다. 삭제하시겠습니까?');
    }
});
document.querySelectorAll('.elements').forEach(function(button) {
    button.addEventListener('click', function() {
        if (categoryModifyForm.style.display === 'block') {
            var categoryLabel = button.textContent; // 현재 클릭한 버튼의 텍스트 가져오기
            document.getElementById('categoryName').innerText = categoryLabel;
            document.querySelector('#downArrow').style.display = 'block';
            document.querySelector('#categoryName').style.display = 'block';
        }
        if(categoryDeleteForm.style.display === 'block'){
            var categoryLabel = button.textContent; // 현재 클릭한 버튼의 텍스트 가져오기
            document.getElementById('categoryDelete').innerText = categoryLabel;
            document.querySelector('#categoryDelete').style.visibility = 'visible';
        }
    });
});