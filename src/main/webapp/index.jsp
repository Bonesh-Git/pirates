<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <title>ثبت نام حساب بانک ملی</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Tahoma, Arial, sans-serif;
            background: linear-gradient(to right, #002147, #0057b8);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 350px;
            box-sizing: border-box;
            text-align: center;
        }

        h2 {
            color: #002147;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
            text-align: right;
            color: #002147;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 2px solid #0057b8;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type="text"]#national_id {
            direction: ltr;
        }

        button {
            width: 100%;
            padding: 12px;
            background: #ffcc00;
            color: #002147;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            margin-top: 15px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background: #ffbb00;
        }

        .error {
            color: red;
            font-size: 13px;
            text-align: right;
            margin-top: 4px;
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>ثبت نام حساب بانک ملی</h2>
    <form id="registerForm" action="RegisterServlet" method="post" novalidate>
        <label for="firstname">: نام</label>
        <input type="text" id="firstname" name="firstname" required>
        <p class="error" id="error-firstname"></p>

        <label for="lastname">: نام خانوادگی</label>
        <input type="text" id="lastname" name="lastname" required>
        <p class="error" id="error-lastname"></p>

        <label for="national_id">: کد ملی</label>
        <input type="text" id="national_id" name="national_id" maxlength="10" inputmode="numeric" required>
        <p class="error" id="error-national-id"></p>

        <label for="cardtype">: نوع کارت</label>
        <select id="cardtype" name="cardtype">
            <option value="DEBIT">کارت اعتباری</option>
            <option value="GIFT">کارت هدیه</option>
        </select>

        <label for="branch">: شعبه</label>
        <select id="branch" name="branch">
            <option value="1" data-location="نارمک">بانک ملی - شعبه نارمک</option>
            <option value="2" data-location="تهرانپارس">بانک ملی - شعبه تهرانپارس</option>
            <option value="3" data-location="تجریش">بانک ملی - شعبه تجریش</option>
            <option value="4" data-location="ونک">بانک ملی - شعبه ونک</option>
        </select>


        <input type="hidden" id="branchId" name="branchId">
        <input type="hidden" id="location" name="location">


        <label for="amount">: مبلغ واریزی (تومان)</label>
        <input type="number" id="amount" name="amount" required>
        <p class="error" id="error-amount"></p>

        <button type="submit">ثبت نام</button>
    </form>
</div>

<script>
    function isValidIranianNationalCode(code) {
        if (!/^\d{10}$/.test(code)) return false;
        const check = parseInt(code[9]);
        const sum = [...code].slice(0, 9).reduce((acc, digit, i) => acc + parseInt(digit) * (10 - i), 0);
        const remainder = sum % 11;
        return (remainder < 2 && check === remainder) || (remainder >= 2 && check === 11 - remainder);
    }

    const form = document.getElementById('registerForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault();


        document.querySelectorAll('.error').forEach(el => {
            el.style.display = 'none';
            el.textContent = '';
        });

        let valid = true;
        const firstname = document.getElementById('firstname').value.trim();
        const lastname = document.getElementById('lastname').value.trim();
        const nationalId = document.getElementById('national_id').value.trim();
        const amount = document.getElementById('amount').value.trim();

        if (!firstname) {
            document.getElementById('error-firstname').textContent = "لطفاً نام را وارد کنید.";
            document.getElementById('error-firstname').style.display = 'block';
            valid = false;
        }

        if (!lastname) {
            document.getElementById('error-lastname').textContent = "لطفاً نام خانوادگی را وارد کنید.";
            document.getElementById('error-lastname').style.display = 'block';
            valid = false;
        }

        if (!/^\d{10}$/.test(nationalId)) {
            document.getElementById('error-national-id').textContent = "کد ملی باید دقیقا ۱۰ رقم عددی باشد.";
            document.getElementById('error-national-id').style.display = 'block';
            valid = false;
        }

        if (isNaN(amount) || Number(amount) <= 0) {
            document.getElementById('error-amount').textContent = "مبلغ واریزی باید یک عدد مثبت باشد.";
            document.getElementById('error-amount').style.display = 'block';
            valid = false;
        }

        const branchSelect = document.getElementById('branch');
        const selectedOption = branchSelect.options[branchSelect.selectedIndex];
        document.getElementById('branchId').value = selectedOption.value;
        document.getElementById('location').value = selectedOption.dataset.location;


        if (valid) {
            form.submit();
        }
    });
</script>
</body>
</html>