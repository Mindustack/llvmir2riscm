; ModuleID = 'Array_pointer.c'
source_filename = "Array_pointer.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca [5 x i32], align 4
  %3 = alloca ptr, align 4
  %4 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 20, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %4) #2
  %5 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 0
  store i32 1, ptr %5, align 4, !tbaa !3
  %6 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 1
  store i32 2, ptr %6, align 4, !tbaa !3
  %7 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 2
  store i32 3, ptr %7, align 4, !tbaa !3
  %8 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 3
  store i32 4, ptr %8, align 4, !tbaa !3
  %9 = getelementptr inbounds [5 x i32], ptr %2, i32 0, i32 4
  store i32 5, ptr %9, align 4, !tbaa !3
  store i32 0, ptr %4, align 4, !tbaa !3
  store ptr %2, ptr %3, align 4, !tbaa !7
  %10 = load ptr, ptr %3, align 4, !tbaa !7
  %11 = getelementptr inbounds [5 x i32], ptr %10, i32 0, i32 4
  %12 = load i32, ptr %11, align 4, !tbaa !3
  %13 = icmp eq i32 %12, 5
  br i1 %13, label %14, label %15

14:                                               ; preds = %0
  store i32 0, ptr %4, align 4, !tbaa !3
  br label %16

15:                                               ; preds = %0
  store i32 -1, ptr %4, align 4, !tbaa !3
  br label %16

16:                                               ; preds = %15, %14
  %17 = load i32, ptr %4, align 4, !tbaa !3
  call void @llvm.lifetime.end.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 20, ptr %2) #2
  ret i32 %17
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
!7 = !{!8, !8, i64 0}
!8 = !{!"any pointer", !5, i64 0}
